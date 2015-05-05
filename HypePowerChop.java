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

@ScriptManifest(author = "Hypo", category = Category.WOODCUTTING, description = "Powerchops willows in draynor", name = "HypePowerChop", servers = { "Ikov" }, version = 1.0)
public class HypePowerChop extends Script {

    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

    private final int Willow_Inventory = 1520;
    private final int Willow_Tree = 5551;
    private final int Hatchet = 1360;

    @Override
    public boolean onExecute() {
        strategies.add(new CutWood());
        strategies.add(new Dropwood());
        provide(strategies);
        return true;
    }



    public class CutWood implements Strategy {
        @Override
        public boolean activate() {
            if (!Inventory.isFull()) {
                return true;
            }
            return false;

        }

        @Override
        public void execute() {
            SceneObject willow = SceneObjects.getClosest(Willow_Tree);
            if (willow != null && Players.getMyPlayer().getAnimation() == -1) {
                Menu.sendAction(502, willow.getHash(), willow.getLocalRegionX(),
                        willow.getLocalRegionY(), Willow_Tree, 1);
                Time.sleep(2000);
            }
        }
    }
        public class Dropwood implements Strategy {
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
