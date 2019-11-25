package GameModel;

import CardModel.wildCard;
import GameView.card;
import Interface.gameConstants;
import Interface.unoConstants;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.stream.Collectors;
import java.util.List;

/******************************************************************************
 * The AI class serves as artificial intelligence player of the game. This
 * class is to be played against the user.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public class AI extends Player implements gameConstants {

    /******************************************************************************
     * Empty default class constructor for set up.
     ******************************************************************************/
    public AI() {
        setName("AI");
        super.setCards();
    }

    /******************************************************************************
     * This method controls the move of the games AI.
     * ** TO BE CORRECTED AND IMPLEMENTED AFTER BASIC FUNC PASSES ENOUGH TESTING **
     * @param topCard - card for play comparision.
     * @return - true if AI can make valid move - else false.
     ******************************************************************************/
    public boolean play(card topCard) {
        boolean done = false;
        final Color currentColor;

        final String value = topCard.getValue();

        if (topCard.getType() == WILD) {
            currentColor = ((wildCard) topCard).getWildColor();
        }
        else
            currentColor = topCard.getColor();


        List<card> tempHand = getPlayerHand().stream().filter(c -> (c.getType() == topCard.getType() && c.getType() != unoConstants.NUMBERS)||
                c.getColor() == currentColor || c.getValue().equals(value)).collect(Collectors.toList());

        if(tempHand.stream().findFirst().isPresent()) {

            try {
                List<card> filterHand = tempHand.stream().filter(c -> c.getType() == card.ACTION).collect(Collectors.toList());

                if (filterHand.stream().findFirst().isPresent()) {
                    computerPressCard(filterHand.get(0));

                } else {
                    computerPressCard(tempHand.get(0));
                }

                done = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // if no card was found, play wild card
        if (!done) {

            tempHand = getPlayerHand().stream().filter(c -> c.getType() == unoConstants.WILD ).collect(Collectors.toList());
            if(tempHand.stream().findFirst().isPresent()) {
                computerPressCard(tempHand.get(0));
            }
        }

        if (getPlayerHandTotal() == 1 || getPlayerHandTotal() == 2)
            setSayUNO(true);


        return done;
    }

    public void computerPressCard(card card) {
        MouseEvent doPress = new MouseEvent(card, MouseEvent.MOUSE_PRESSED,
                System.currentTimeMillis(),
                (int) MouseEvent.MOUSE_EVENT_MASK, 5, 5, 1, true);
                card.dispatchEvent(doPress);


        MouseEvent doRelease = new MouseEvent(card, MouseEvent.MOUSE_RELEASED,
                System.currentTimeMillis(),
                (int) MouseEvent.MOUSE_EVENT_MASK, 5, 5, 1, true);
        card.dispatchEvent(doRelease);

    }

}
