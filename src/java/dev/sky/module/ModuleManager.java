package dev.sky.module;

import com.cubk.event.annotations.EventTarget;
import dev.sky.Client;
import dev.sky.events.impls.misc.EventKey;
import dev.sky.module.impls.display.ClickGui;
import dev.sky.module.impls.display.HUDMod;
import dev.sky.module.impls.player.Sprint;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


@Getter
public class ModuleManager  {
    CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>();

    public ModuleManager() {
        Client.INSTANCE.getEventManager().register(this);

        modules.add(new ClickGui());
        modules.add(new HUDMod());
        modules.add(new Sprint());
    }

    public Module getModule(String name) {
        for (Module m : modules) if(m.getName().equalsIgnoreCase(name)) return m;
        return null;
    }
    public List<Module> getModulesInCategory(Category c) {
        return this.modules.stream().filter(module -> module.getCategory() == c).collect(Collectors.toList());
    }

    @EventTarget
    void onKey(EventKey e) {
        for (Module module : modules) if (module.getKeyCode() == e.getKeyCode()) module.toggle();
    }


}