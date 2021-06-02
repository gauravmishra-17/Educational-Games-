package fullstacktraining.springboot.react.util;

import fullstacktraining.springboot.react.model.Game;

public class GameCreator {

    public static Game createGameToBeSaved(){
        return Game.builder().title("Mandala")
                .description("Mandala Kids With the game Mandala Kids paints various mandalas in a " +
                        "fun way on your phone, tablet or computer.")
                .gameUrl("https://html5.gamedistribution.com/340f801248cd43cf99709a9aa957a1d4/,")
                .pictureUrl("https://img.gamedistribution.com/340f801248cd43cf99709a9aa957a1d4-512x512.jpeg")
                .genre("F").build();
    }

    public static Game createValidGame(){
        return Game.builder().title("Mandala")
                .description("Mandala Kids With the game Mandala Kids paints various mandalas in a " +
                        "fun way on your phone, tablet or computer.")
                .gameUrl("https://html5.gamedistribution.com/340f801248cd43cf99709a9aa957a1d4/,")
                .pictureUrl("https://img.gamedistribution.com/340f801248cd43cf99709a9aa957a1d4-512x512.jpeg")
                .id(1l)
                .genre("F").build();
    }

    public static Game createValidUpdatedGame(){
        return Game.builder().title("Mandala 2")
                .description("Mandala Kids paints various mandalas in a fun way on your phone, tablet or computer.")
                .gameUrl("https://html5.gamedistribution.com/340f801248cd43cf99709a9aa957a1d4/,")
                .pictureUrl("https://img.gamedistribution.com/340f801248cd43cf99709a9aa957a1d4-512x512.jpeg")
                .id(1l)
                .genre("F").build();
    }
}
