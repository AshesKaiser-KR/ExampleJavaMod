package ages.type;

import arc.Core;
import arc.struct.Seq;
import mindustry.*;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.type.*;

import static ages.util.Useful.*;

public class Focus extends UnlockableContent{
    public int addSectors = 1;
    public ItemStack[] requirements;
    public ItemStack[] rewards;
    /* contents to unlock together when unlocked */
    public Seq<UnlockableContent> unlockContents = new Seq<>();

    public Focus(String name){
        super(name);

        hideDetails = false;
        this.localizedName = Core.bundle.get("focus." + this.name + ".name", this.name);
        this.description = Core.bundle.get("focus." + this.name + ".description");
        this.details = Core.bundle.getOrNull("focus." + this.name + ".details");
    }

    public void reward(ItemStack[] stack){
        this.rewards = stack;
    }

    public void unlock(UnlockableContent... content){
        for (UnlockableContent i: content){
            this.unlockContents.add(i);
        }
    }

    public void requirements(ItemStack... stack){
        this.requirements = stack;
    }

    @Override
    public void onUnlock(){
        for (UnlockableContent content: this.unlockContents){
            if (content != null) content.unlock();
        }
    }

    @Override
    public void clearUnlock(){
        super.clearUnlock();

        for (Item i: Vars.content.items()){
            Core.settings.put(i.localizedName, 0);
        }
    }

    @Override
    public ItemStack[] researchRequirements(){
        return requirements;
    }

    @Override
    public ContentType getContentType(){
        return ContentType.typeid_UNUSED;
    }
}
