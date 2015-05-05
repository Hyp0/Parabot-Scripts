import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

import java.util.ArrayList;

@ScriptManifest(author = "Hypo", category = Category.MINING, description = "Powermines Iron", name = "HypeMiner", servers = { "Ikov" }, version = 1.0)
public class HypeMiner extends Script {

    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

    private final int Iron_Inventory = 441;
    private final int Iron_Rock = 2092;
    private final int Pickaxe = 1276;

    @Override
    public boolean onExecute() {
        strategies.add(new MineOre());
        strategies.add(new drop());
        provide(strategies);
        return true;
    }

    @Override
    public void onFinish() {

    }

    public class MineOre implements Strategy {
        @Override
        public boolean activate() {
            if (!Inventory.isFull()) {
                return true;
            }
            return false;

        }

        @Override
        public void execute() {
            SceneObject iron = SceneObjects.getClosest(Iron_Rock);
            if (iron != null && Players.getMyPlayer().getAnimation() == -1) {
                Menu.sendAction(502, iron.getHash(), iron.getLocalRegionX(),
                        iron.getLocalRegionY(), Iron_Rock, 1);
                Time.sleep(2000);


            }
        }
    }
    public class drop implements Strategy {



        @Override
        public boolean activate() {
            return Inventory.isFull();

        }

        @Override
        public void execute() {
            Keyboard.getInstance().sendKeys("::empty");
            Time.sleep(100);

        }

    }

}



