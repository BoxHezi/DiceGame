package view.implgui;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.implgui.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private GameEngine gameEngine;

    public GameEngineCallbackGUI(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine) {

    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine) {

    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {

    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine) {

    }
}
