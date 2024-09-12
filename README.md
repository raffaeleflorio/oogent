# OOGent (Object-Oriented aGent)

OOGent is a minimal Java 21 library useful to build LLM agents. It's written with Object-Oriented principles in mind.
It encapsulates [langchain4j](https://docs.langchain4j.dev) to communicate with LLMs.

It's work in progress.

# Features

## Simple Prompt

```java
public static void main(final String[] args) {
    var llm = new L4JLLM(
            OllamaChatModel.builder().modelName("llama3.1:8b").baseUrl("http://127.0.0.1:11434").build()
    );
    var promptTemplate = new L4JPromptTemplate("""
            Summarise the following text in a single sentence. Write only the summary.
            Text: {{it}}
            Summary:
            """
    );
    var agent = new PromptAgent(llm, promptTemplate);
    var response = agent.response(
            // from: https://en.wikipedia.org/wiki/SSC_Napoli
            new PlainText("""
                    Società Sportiva Calcio Napoli (pronounced [ˈnaːpoli]) is an Italian professional football club based in the city of Naples that plays in Serie A, the top flight of Italian football.
                    In its history, Napoli has won three Serie A titles, six Coppa Italia titles, two Supercoppa Italiana titles, and one UEFA Cup.[1]
                    The club was formed in 1926 as Associazione Calcio Napoli following the merger of US Internazionale Napoli and Naples Foot-Ball Club.
                    Napoli saw relatively little success in its early years, winning their first major trophy in the 1962 Coppa Italia.
                    Napoli then saw increased success in the late 1970s (including their second Coppa Italia in 1976) and especially in the 1980s, after the club acquired Diego Maradona in 1984.
                    During his time in Naples, Maradona helped the team win several trophies, which led to the club retiring his number 10 jersey.
                    During this period, Napoli won two league titles (in 1987 and 1990), the 1987 Coppa Italia, the 1990 Supercoppa Italiana, and their only European trophy with the 1989 UEFA Cup.
                    Following his departure, however, Napoli struggled financially, and endured several relegations and a bankruptcy, prior to being re-founded in 2004 by film producer Aurelio De Laurentiis.
                    Under his leadership, the club has stabilized, which has led to renewed on-field success, winning 2005–06 Serie C1, the 2012, 2014, and 2020 Coppa Italia titles, and the 2014 Supercoppa Italiana, eventually culminating in their third league title in 2023, the first since Maradona's departure.
                    """
            )
    );
    System.out.println(response.asString());
    /*
        Società Sportiva Calcio Napoli is an Italian professional football club based in Naples that has won three Serie A titles and a range of other domestic and European honors throughout its history.
    */
}
```

## Prompt Chaining

```java
public static void main(final String[] args) {
    var chatLanguageModel = OllamaChatModel.builder().modelName("llama3.1:8b").baseUrl("http://127.0.0.1:11434").build();
    var agent = new ChainAgent(
            new PromptAgent(
                    new L4JLLM(chatLanguageModel),
                    new L4JPromptTemplate("""
                            Fix typos in the following text word by word. If a word doesn't contain a typo repeat it. \
                            Write only the fixed text.
                            Text: {{it}}
                            Fixed text:
                            """
                    )
            ),
            new PromptAgent(
                    new L4JLLM(chatLanguageModel),
                    new L4JPromptTemplate("""
                            You are a helpful assistant. Answer as best as possible. \
                            You answer by telling a short joyful anecdote as if you experienced it.
                            Question: {{it}}
                            Answer:
                            """)
            )
    );
    var response = agent.response(
            new PlainText("""
                    when was te frst Java vrson releaeaased?
                    """
            )
    );
    System.out.println(response.asString());
    /*
        The nostalgic tale of "Java's First Steps"!

        I still remember the day I stumbled upon that quaint little coffee shop in California, where James Gosling and his team were brewing up something new. It was May 23, 1995! Can you believe it?

        As I walked in, I saw a group of excited developers huddled around a computer screen, typing away furiously. They told me they were working on this thing called "Java" – a language that would allow anyone to write programs that could run on any device with a Java Virtual Machine (JVM).

        I watched in awe as James himself typed out the first lines of code for Java 1.0. The excitement was palpable! They shared their dreams of making programming accessible to everyone, and I knew right then that this little coffee shop moment would go down in history.

        And so, on May 23, 1995, Java 1.0 was born – a day that marked the beginning of an incredible journey for the world of software development!
     */
}
```

## Conversational Agent

```java
public static void main(final String[] args) {
    var chatLanguageModel = OllamaChatModel.builder().modelName("llama3.1:8b").baseUrl("http://127.0.0.1:11434").build();
    var conversation = new InMemoryConversation()
            .then(new HumanMessage("What does LLM stand for?"))
            .then(new AiMessage("LLM stands for \"Large Language Model.\""));
    var agent = new ChainAgent(
            new ConversationalAgent(
                    conversation,
                    new PlainText("User: "),
                    new PlainText("AI: ")
            ),
            new PromptAgent(
                    new L4JLLM(chatLanguageModel),
                    new L4JPromptTemplate("""
                            You are the AI that shortly answer the User question.
                            {{it}}
                            AI:
                            """)
            )
    );
    var response = agent.response(
            new PlainText("""
                    Where are they used?
                    """)
    );
    System.out.println(response.asString());
    /*
        LLMs are used in various applications such as chatbots, virtual assistants, language translation software, and text summarization tools. They're also employed in customer service, content generation, and even educational platforms.
     */
}
```

## RAG

```java
public static void main(final String[] args) {
    var storage = new InMemoryVectorStorage(
            new L4JEmbeddingModel(
                    OllamaEmbeddingModel.builder().modelName("mxbai-embed-large:335m").baseUrl("http://127.0.0.1:11434").build()
            ),
            new CosineSimilarityScore()
    );
    storage.store(
            new TextDocument(
                    new Cached(
                            new TextFile("/path/to/oogentDescription.txt")
                    ),
                    new PlainText("oogentDescription.txt"),
                    text -> new FixedSizeChunks(text, 128)
            )
    );
    storage.store(
            new TextDocument(
                    new Cached(
                            new PDF("/path/to/UEFA_Euro_2024_Final_Intro.pdf") // content from: https://en.wikipedia.org/wiki/UEFA_Euro_2024_final
                    ),
                    new PlainText("UEFA_Euro_2024_Final_Intro.pdf"),
                    text -> new SeparatedChunks(text, new PlainText("\n"))
            )
    );
    var agent = new RAGAgent(
            new L4JLLM(OllamaChatModel.builder().modelName("llama3.1:8b").baseUrl("http://127.0.0.1:11434").build()),
            new L4JPromptTemplate("""
                    You answer the Question by using the Reliable sources you found. Write just the answer.
                    Question: {{input}}
                    Reliable sources:
                    {{context}}
                    Answer:
                    """
            ),
            new StorageAgent(
                    storage,
                    0.8d,
                    10
            )
    );
    var oogentResponse = agent.response(
            new PlainText("""
                    Could I use oogent with Java?
                    """
            )
    );
    oogentResponse.sources().forEach(source -> System.out.println(source.id().asString()));
    /*
        oogentDescription.txt
     */
    System.out.println(oogentResponse.asString());
    /*
        Yes, you can use OOGent with Java, specifically for building Large Language Model (LLM) agents.
     */

    var euro2024Response = agent.response(
            new PlainText("""
                    Who scored the 2-1 goal in the Euro 2024 final?
                    """
            )
    );
    euro2024Response.sources().forEach(source -> System.out.println(source.id().asString()));
    /*
        UEFA_Euro_2024_Final_Intro.pdf
     */
    System.out.println(euro2024Response.asString());
    /*
        Mikel Oyarzabal
     */
}
```

## ReAct

```java
public static void main(final String[] args) {
    var agent = new ReActAgent(
            new Toolbox(
                    new Tool() {
                        @Override
                        public Text id() {
                            return new PlainText("sqrt");
                        }

                        @Override
                        public Text signature() {
                            return new PlainText("sqrt: Computes the square root of a number. It accepts a single numerical arguments (e.g., 4).");
                        }

                        @Override
                        public Text result(final Text args) {
                            var sqrt = Math.sqrt(Double.parseDouble(args.asString()));
                            return new PlainText(String.valueOf(sqrt));
                        }
                    },
                    new Tool() {
                        @Override
                        public Text id() {
                            return new PlainText("sum");
                        }

                        @Override
                        public Text signature() {
                            return new PlainText("sum: Computes the sum between two or more numbers. It accepts two or more numerical arguments delimited by a comma (e..g, 123, 456, 789)");
                        }

                        @Override
                        public Text result(final Text args) {
                            var numbers = args.asString().split(",");
                            var result = 0.0d;
                            for (var number : numbers) {
                                result += Double.parseDouble(number);
                            }
                            return new PlainText(String.valueOf(result));
                        }
                    }
            ),
            new L4JLLM(OllamaChatModel.builder().stop(List.of("Output:")).modelName("llama3.1:8b").baseUrl("http://127.0.0.1:11434").build()),
            new L4JPromptTemplate("""
                    Answer the user as best as possible. You could also execute the followings tools in order to provide the most accurate answer:
                    {{tools}}
                                            
                    Answer step-by-step by following this format:
                    Thought: your thoughts on what to do
                    Tool: one of the above tool name. You have to skip this step if no one of the above tool is needed.
                    Args: the previous Tool arguments. You have to skip this step if no Tool is needed.
                    Output: the previous Tool output. You have to skip this step if no Tool is needed.
                    ... Other N Thought steps...
                    Thought: Great, I know the answer!
                    Answer: your answer to the user
                                            
                    It's really important to follow the above format. So, don't include unnecessary or redundant steps.
                                            
                    So, here you are the User request.
                                            
                    User: {{input}}
                    Thought:
                    """)
    );
    System.out.println(agent.response(new PlainText("Hi!")).asString());
    /*
        Hi back! How can I assist you today?
     */
    System.out.println(agent.response(new PlainText("What is the square root of 1764?")).asString());
    /*
        The square root of 1764 is 42.0.
     */
    System.out.println(agent.response(new PlainText("What is the square root of the sum between 2 and 5?")).asString());
    /*
        The square root of the sum between 2 and 5 is approximately 2.65.
     */
}
```

## Functional Agent

```java
public static void main(final String[] args) {
    var agent = new FunctionalAgent(
            text -> new TextResponse(new Lowered(new Trimmed(text)))
    );
    var response = agent.response(
            new PlainText("""
                    THIS IS UPPERCASE
                    """
            )
    );
    System.out.println(response.asString());
    /*
        this is uppercase
     */
}
```

## Conditional Agent

TODO