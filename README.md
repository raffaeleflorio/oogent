# OOGent (Object-Oriented aGent)

OOGent is a minimal Java 21 library to build LLM agents. It's written with Object-Oriented principle in mind.
It relies on [langchain4j](https://docs.langchain4j.dev) to communicate with an LLM.

# Features

- prompt chaining
- RAG
- ReAct agents (WIP)
- Real RAG storage (WIP)

# Examples

## Simple prompt

```java
public final class Main {

    public static void main(final String[] args) {
        var llm = new Langchain4JLLM(
                OllamaChatModel.builder().modelName("llama3").baseUrl("http://127.0.0.1:11434").build()
        );
        var promptTemplate = new Langchain4JPromptTemplate("""
                Summarise the following text in a single phrase.
                Text: {{it}}
                """
        );
        var response = new PromptAgent(llm, promptTemplate)
                .response(
                        new SimpleText("""
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
        System.out.println(response.text());
    }
}
```

## Prompt chaining

```java
public static void main(final String[] args) {
    var chatLanguageModel = OllamaChatModel.builder().modelName("llama3").baseUrl("http://127.0.0.1:11434").build();
    var agent = new ChainAgent(
            new PromptAgent(
                    new Langchain4JLLM(chatLanguageModel),
                    new Langchain4JPromptTemplate("""
                            Summarise the following text in a single phrase.
                            Text: {{it}}
                            """
                    )
            ),
            new PromptAgent(
                    new Langchain4JLLM(chatLanguageModel),
                    new Langchain4JPromptTemplate("""
                            Fix typo word by word. Write just the corrected text.
                            Text: {{it}}
                            Corrected text:
                            """
                    )
            )
    );
    var response = agent
            .response(
                    new SimpleText("""
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
    System.out.println(response.text());
}
```

## RAG and prompt chaining

```java
public static void main(final String[] args) {
    var chatLanguageModel = OllamaChatModel.builder().modelName("llama3").baseUrl("http://127.0.0.1:11434").build();
    var agent = new ChainAgent(
            new PromptAgent(
                    new Langchain4JLLM(chatLanguageModel),
                    new Langchain4JPromptTemplate("""
                            Fix typo word by word. Write just the corrected text.
                            Text: {{it}}
                            Corrected text:
                            """
                    )
            ),
            new RAGAgent(
                    new FnMapStorage(
                            Map.of(
                                    text -> text.text().toLowerCase().contains("napoli"),
                                    List.of(
                                            new SimpleText("Società Sportiva Calcio Napoli (pronounced [ˈnaːpoli]) is an Italian professional football club based in the city of Naples that plays in Serie A, the top flight of Italian football."),
                                            new SimpleText("In its history, Napoli has won three Serie A titles, six Coppa Italia titles, two Supercoppa Italiana titles, and one UEFA Cup.[1]"),
                                            new SimpleText("The club was formed in 1926 as Associazione Calcio Napoli following the merger of US Internazionale Napoli and Naples Foot-Ball Club."),
                                            new SimpleText("Napoli saw relatively little success in its early years, winning their first major trophy in the 1962 Coppa Italia."),
                                            new SimpleText("Napoli then saw increased success in the late 1970s (including their second Coppa Italia in 1976) and especially in the 1980s, after the club acquired Diego Maradona in 1984."),
                                            new SimpleText("During his time in Naples, Maradona helped the team win several trophies, which led to the club retiring his number 10 jersey."),
                                            new SimpleText("During this period, Napoli won two league titles (in 1987 and 1990), the 1987 Coppa Italia, the 1990 Supercoppa Italiana, and their only European trophy with the 1989 UEFA Cup."),
                                            new SimpleText("Following his departure, however, Napoli struggled financially, and endured several relegations and a bankruptcy, prior to being re-founded in 2004 by film producer Aurelio De Laurentiis."),
                                            new SimpleText("Under his leadership, the club has stabilized, which has led to renewed on-field success, winning 2005–06 Serie C1, the 2012, 2014, and 2020 Coppa Italia titles, and the 2014 Supercoppa Italiana, eventually culminating in their third league title in 2023, the first since Maradona's departure.")
                                    )
                            )
                    ),
                    new Langchain4JLLM(chatLanguageModel),
                    new Langchain4JPromptTemplate("""
                            Answer the following request using only the reliable sources.
                            Reliable sources: {{context}}
                            Request: {{text}}
                            Response:
                            """)
            )
    );
    var response = agent.response(new SimpleText("How maaany titles the Napule won?"));
    System.out.println(response.text());
}
```