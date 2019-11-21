package GameModel;

import GameView.card;
import Interface.gameConstants;

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
//
//        Color color = topCard.getColor();
//        String value = topCard.getValue();
//
//        if(topCard.getType()==WILD){
//            color = ((wildCard) topCard).getWildColor();
//        }
//
//        for (card card : getPlayerHand()) {
//
//            if (card.getColor().equals(color) || card.getValue().equals(value)) {
//
//                MouseEvent doPress = new MouseEvent(card, MouseEvent.MOUSE_PRESSED,
//                        System.currentTimeMillis(),
//                        (int) MouseEvent.MOUSE_EVENT_MASK, 5, 5, 1, true);
//                card.dispatchEvent(doPress);
//
//                MouseEvent doRelease = new MouseEvent(card, MouseEvent.MOUSE_RELEASED,
//                        System.currentTimeMillis(),
//                        (int) MouseEvent.MOUSE_EVENT_MASK, 5, 5, 1, true);
//                card.dispatchEvent(doRelease);
//
//                done = true;
//                break;
//            }
//        }
//
//        // if no card was found, play wild card
//        if (!done) {
//            for (card card : getPlayerHand()) {
//                if (card.getType() == WILD) {
//                    MouseEvent doPress = new MouseEvent(card,
//                            MouseEvent.MOUSE_PRESSED,
//                            System.currentTimeMillis(),
//                            (int) MouseEvent.MOUSE_EVENT_MASK, 5, 5, 1, true);
//                    card.dispatchEvent(doPress);
//
//                    MouseEvent doRelease = new MouseEvent(card, MouseEvent.MOUSE_RELEASED,
//                            System.currentTimeMillis(),
//                            (int) MouseEvent.MOUSE_EVENT_MASK, 5, 5, 1, true);
//                    card.dispatchEvent(doRelease);
//
//                    done = true;
//                    break;
//                }
//            }
//        }
//
//        if(getPlayerHandTotal()==1 || getPlayerHandTotal()==2)
//            setSayUNO(true);
//
        return done;
    }

}
