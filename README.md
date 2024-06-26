# OOGent (Object-Oriented aGent)

OOGent is a minimal Java 21 library useful to build LLM agents. It's written with Object-Oriented principles in mind.
It relies on [langchain4j](https://docs.langchain4j.dev) to communicate with an LLM.

It's work in progress.

# Features

- prompt chaining
- RAG
- Conversational agents
- Embedding models (WIP)
- ReAct agents (WIP)
- Real `Storage` implementation (WIP)
- Real `Conversations` implementation (WIP)

# Examples

## Simple prompt

```java
public static void main(final String[] args) {
    var llm = new L4JLLM(
            OllamaChatModel.builder().modelName("llama3:8b").baseUrl("http://127.0.0.1:11434").build()
    );
    var promptTemplate = new L4JPromptTemplate("""
            Summarise the following text in a single phrase.
            Text: {{it}}
            """
    );
    var response = new PromptAgent(llm, promptTemplate)
            .response(
                    new PlainText("""
                            Società Sportiva Calcio Napoli (pronounced [ˈnaːpoli]) is an Italian professional football club based in the city of Naples that plays in Serie A, the top flight of Italian football.
                            In its history, Napoli has won three Serie A titles, six Coppa Italia titles, two Supercoppa Italiana titles, and one UEFA Cup.[1]
                            The club was formed in 1926 as Associazione Calcio Napoli following the merger of US Internazionale Napoli and Naples Foot-Ball Club.
                            Napoli saw relatively little success in its early years, winning their first major trophy in the 1962 Coppa Italia.
                            Napoli then saw increased success in the late 1970s (including their second Coppa Italia in 1976) and especially in the 1980s, after the club acquired Diego Maradona in 1984.
                            During his time in Naples, Maradona helped the team win several trophies, which led to the club retiring his number 10 jersey.
                            During this period, Napoli won two league titles (in 1987 and 1990), the 1987 Coppa Italia, the 1990 Supercoppa Italiana, and their only European trophy with the 1989 UEFA Cup.
                            Following his departure, however, Napoli struggled financially, and endured several relegations and a bankruptcy, prior to being re-founded in 2004 by film producer Aurelio De Laurentiis.
                            Under his leadership, the club has stabilized, which has led to renewed on-field success, winning 2005–06 Serie C1, the 2012, 2014, and 2020 Coppa Italia titles, and the 2014 Supercoppa Italiana, eventually culminating in their third league title in 2023, the first since Maradona's departure."""
                    )
            );
    System.out.println(response.asString());
    /*
         Napoli is a historic Italian football club that has experienced periods of success and struggles over its nearly century-long history.
    */
}
```

## Prompt chaining

```java
public static void main(final String[] args) {
    var chatLanguageModel = OllamaChatModel.builder().modelName("llama3:8b").baseUrl("http://127.0.0.1:11434").build();
    var agent = new ChainAgent(
            new PromptAgent(
                    new L4JLLM(chatLanguageModel),
                    new L4JPromptTemplate("""
                            Summarise the following text in two phrases.
                            Text: {{it}}
                            """
                    )
            ),
            new PromptAgent(
                    new L4JLLM(chatLanguageModel),
                    new L4JPromptTemplate("""
                            Identify a list of keyword from the following text.
                            Text: {{it}}
                            Keywords:
                            """
                    )
            )
    );
    var response = agent.response(
                    new PlainText("""
                            Società Sportiva Calcio Napoli (pronounced [ˈnaːpoli]) is an Italian professional football club based in the city of Naples that plays in Serie A, the top flight of Italian football.
                            In its history, Napoli has won three Serie A titles, six Coppa Italia titles, two Supercoppa Italiana titles, and one UEFA Cup.[1]
                            The club was formed in 1926 as Associazione Calcio Napoli following the merger of US Internazionale Napoli and Naples Foot-Ball Club.
                            Napoli saw relatively little success in its early years, winning their first major trophy in the 1962 Coppa Italia.
                            Napoli then saw increased success in the late 1970s (including their second Coppa Italia in 1976) and especially in the 1980s, after the club acquired Diego Maradona in 1984.
                            During his time in Naples, Maradona helped the team win several trophies, which led to the club retiring his number 10 jersey.
                            During this period, Napoli won two league titles (in 1987 and 1990), the 1987 Coppa Italia, the 1990 Supercoppa Italiana, and their only European trophy with the 1989 UEFA Cup.
                            Following his departure, however, Napoli struggled financially, and endured several relegations and a bankruptcy, prior to being re-founded in 2004 by film producer Aurelio De Laurentiis.
                            Under his leadership, the club has stabilized, which has led to renewed on-field success, winning 2005–06 Serie C1, the 2012, 2014, and 2020 Coppa Italia titles, and the 2014 Supercoppa Italiana, eventually culminating in their third league title in 2023, the first since Maradona's departure."""
                    )
            );
    System.out.println(response.asString());
    /*
            Here are the keywords I've identified from the text:

            1. Napoli
            2. Italian football club
            3. Aurelio De Laurentiis
            4. Domestic trophies
            5. International trophies
     */
}
```

## RAG

```java
public static void main(final String[] args) {
    var chatLanguageModel = OllamaChatModel.builder().modelName("llama3:8b").baseUrl("http://127.0.0.1:11434").build();
    var agent = new RAGAgent(
            new FunctionalMapStorage(
                    Map.of(
                            text -> text.contains(new PlainText("oogent")),
                            List.of(
                                    new PlainText("oogent stands for Object-Oriented aGent."),
                                    new PlainText("oggent is a minimal 21 Java library useful to build LLM agents."),
                                    new PlainText("oogent uses langchain4j to communicate with LLM.")
                            )
                    )
            ),
            new L4JLLM(chatLanguageModel),
            new L4JPromptTemplate("""
                    Answer the following request using only the given reliable sources.
                    Reliable sources: {{context}}
                    Request: {{text}}
                    Response:
                    """)
    );
    var response = agent.response(new PlainText("What does oogent mean?"));
    System.out.println(response.asString());
    /*
            According to the reliable source, oogent stands for "Object-Oriented aGent".
     */
}
```

## Conversational and RAG

```java
public static void main(final String[] args) {
    var chatLanguageModel = OllamaChatModel.builder().modelName("llama3:8b").baseUrl("http://127.0.0.1:11434").build();
    var conversations = new InMemoryConversations();
    var conversation = conversations.conversation()
            .with(new HumanMessage("What does oogent mean?"))
            .with(new AiMessage("""
                    According to the reliable source, oogent stands for "Object-Oriented aGent".
                    """));
    var agent = new ChainAgent(
            new ConversationalAgent(conversation, "Human: ", "AI: "),
            new PromptAgent(
                    new L4JLLM(chatLanguageModel),
                    new L4JPromptTemplate("""
                            Analyse the conversation between Human and AI by identifying keywords and context. Do it step-by-step.
                            Then, considering the conversation analysis, rephrase the last Human request to be a self-contained request. Do it step-by-step.
                                                            
                            The format you have to follow is:
                            Conversation:
                            ---
                            The Human and AI conversation
                            ---
                            Thought: a thought about your task and how you can solve it.
                            ... other N Thought
                            Thought: I know how to rephrase the last Human request.
                            Rephrase: the self-contained rephrase of the last Human request. It's really important to just write here the rephrase.
                                                            
                            Let's start!
                                                            
                            Conversation:
                            ---
                            {{it}}
                            ---
                            Thought:
                            """)
            ),
            new IfAgent(
                    text -> text.asString().toLowerCase().contains("rephrase:"),
                    new ChainAgent(
                            new FunctionalAgent(
                                    text -> new TextResponse(text.afterLast(new PlainText("Rephrase:")))
                            ),
                            new RAGAgent(
                                    new FunctionalMapStorage(
                                            Map.of(
                                                    text -> text.contains(new PlainText("oogent")),
                                                    List.of(
                                                            new PlainText("oogent stands for Object-Oriented aGent."),
                                                            new PlainText("oggent is a minimal 21 Java library useful to build LLM agents."),
                                                            new PlainText("oogent uses langchain4j to communicate with LLM.")
                                                    )
                                            )
                                    ),
                                    new L4JLLM(chatLanguageModel),
                                    new L4JPromptTemplate("""
                                            Answer the following request using only the given reliable sources.
                                            Reliable sources: {{context}}
                                            Request: {{text}}
                                            Response:
                                            """)
                            )
                    ),
                    new ConstAgent(new TextResponse("Sorry, I didn't understand your request. Could you be more specific?"))
            )
    );

    var newMessage = new HumanMessage("and is it compatible with which language?");
    var response = agent.response(newMessage);
    conversations.save(conversation.with(newMessage).with(new AiMessage(response.asString())));
    System.out.println(response.asString());
    /*
        According to the reliable source, oogent (Object-Oriented aGent) is a minimal 21 Java library. Therefore, based on this information, we can conclude that oogent is compatible with the Java programming language.
     */
}
```

## ReAct

```java
public static void main(final String[] args) {
    var chatLanguageModel = OllamaChatModel.builder()
            .modelName("qwen2:7b")
            .baseUrl("http://127.0.0.1:11434")
            .stop(List.of("Output: "))
            .temperature(0.0)
            .build();
    var agent = new ReActAgent(
            new L4JLLM(chatLanguageModel),
            new L4JPromptTemplate("""
                    Answer the given request. You have access to the following tools:
                    {{actions}}
                                            
                    The format you have to follow is:
                    Request: the request to answer.
                    Thought: a thought about what you need to do to answer the request.
                    Tool: one of the above tool you need to use.
                    Input: the input to the previous tool.
                    Output: the output of the previous tool.
                    ... other N Thought and Tool
                    Thought: now I know the answer!
                    Answer: the answer
                                       
                    It's really important to follow the aforesaid format. You don't need to prepend or append nothing.
                                            
                    Let's begin!
                               
                    Request: {{text}}                             
                    Thought:
                    """),
            action -> new PlainText("- ").then(action.id()).then(new PlainText(": ")).then(action.description()),
            new PlainText("Answer: "),
            new PlainText("Tool: "),
            new PlainText("Input: "),
            new PlainText("Output: "),
            new PlainText("Thought: "),
            new ActionAgent(
                    new FunctionalAgent(
                            () -> new TextResponse(
                                    "Today is ".concat(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH))
                            )
                    ),
                    "currentDay",
                    "retrieves which is the current day (e.g., Saturday)"
            ),
            new ActionAgent(
                    new FunctionalAgent(
                            () -> new TextResponse(
                                    "The current time is ".concat(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")))
                            )
                    ),
                    "currentTime",
                    "retrieves the current time (e.g., 10:52)"
            )
    );
    System.out.println(agent.response(new PlainText("Hi")).asString());
    /*
            Hello! How can I assist you today?
     */
    System.out.println(agent.response(new PlainText("What day is it?")).asString());
    /*
            It's Tuesday.
     */
    System.out.println(agent.response(new PlainText("What time is it?")).asString());
    /*
            It's 10:05.
     */
    System.out.println(agent.response(new PlainText("What day and what time is it?")).asString());
    /*
            Today is Tuesday and the current time is 10:05.
     */
}
```