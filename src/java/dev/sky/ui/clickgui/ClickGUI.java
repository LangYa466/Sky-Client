package dev.sky.ui.clickgui;

import dev.sky.Client;
import dev.sky.module.Category;
import dev.sky.module.Module;
import dev.sky.module.values.AbstractValue;
import dev.sky.module.values.BoolValue;
import dev.sky.module.values.IntValue;
import dev.sky.module.values.StringValue;
import dev.sky.ui.imgui.ImGuiRenderer;
import imgui.ImGui;
import imgui.flag.ImGuiInputTextFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import imgui.type.ImString;
import net.minecraft.client.gui.GuiScreen;

import java.util.HashMap;

public class ClickGUI extends GuiScreen {
    private final HashMap<AbstractValue<?>, Object> settingsMap = new HashMap<>();

    private void renderCategoryModules(Category category) {
        for (Module module : Client.INSTANCE.getModuleManager().getModulesInCategory(category)) {
            if (ImGui.collapsingHeader(module.getName())) {
                ImGui.indent();
                renderModule(module);
                ImGui.unindent();
            }
        }
    }

    private void renderModule(Module module) {
        if (ImGui.isItemHovered()) {
            ImGui.setTooltip(module.getDescription());
        }
        ImGui.pushID(module.getName());
        if (ImGui.checkbox("Enabled", module.isState())) {
            module.toggle();
        }
        for (AbstractValue setting : module.getValues()) {

            if (setting.getClass().equals(BoolValue.class)) {
                BoolValue value = (BoolValue) setting;
                ImBoolean booleanValue = (ImBoolean) settingsMap.getOrDefault(setting, new ImBoolean(value.get()));
                settingsMap.put(setting, booleanValue);
                ImGui.pushID(module.getName() + value.getName());
                ImGui.checkbox(((BoolValue) setting).getName(), booleanValue);
                ImGui.popID();
                if (value.get() != booleanValue.get()) {
                    value.set(booleanValue.get());
                }
            } else if (setting.getClass().equals(IntValue.class)) {
                IntValue value = (IntValue) setting;
                float[] floatValue = (float[]) settingsMap.getOrDefault(setting, new float[]{value.get().floatValue()});
                settingsMap.put(setting, floatValue);
                ImGui.pushID(module.getName() + value.getName());
                ImGui.sliderFloat(((IntValue) setting).getName(), floatValue, value.getMinimum(),
                        value.getMaximum());
                ImGui.popID();
                if (floatValue[0] != value.get().floatValue()) {
                    value.set((int) floatValue[0]);
                }
            } else if (setting.getClass().equals(StringValue.class)) {
                StringValue value = (StringValue) setting;
                ImInt modeIndex = (ImInt) settingsMap.getOrDefault(setting, new ImInt(value.getValue().indexOf(value.get())));
                settingsMap.put(setting, modeIndex);
                String modeNames = value.get();
                ImGui.pushID(module.getName() + value.getName());
                ImGui.combo(((StringValue) setting).getName(), modeIndex, modeNames);
                ImGui.popID();
                if (modeIndex.get() != value.getValue().indexOf(value.get())) {
                    value.set(value);
                }
            } else if (setting instanceof StringValue) {
                StringValue value = (StringValue) setting;
                settingsMap.put(setting, value.get());
                ImGui.text(value.getName());
                ImGui.sameLine();
                ImGui.pushID(module.getName() + value.getName());
                ImGui.setNextItemWidth(ImGui.getContentRegionAvail().x);
                ImGui.inputText("", new ImString(value.getValue()), ImGuiInputTextFlags.CallbackResize);
                ImGui.popID();
            }


        }
        ImGui.popID();

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Runnable runnable = () -> {
            if (ImGui.begin("langya.ink", ImGuiWindowFlags.AlwaysAutoResize | ImGuiWindowFlags.NoCollapse)) {
                ImGui.styleColorsLight();
                if (ImGui.beginTabBar("Modules")) {
                    for (Category type : Category.values()) {
                        if (ImGui.beginTabItem(type.name())) {
                            renderCategoryModules(type);
                            ImGui.endTabItem();
                        }
                    }
                    if (ImGui.beginTabItem("Config")) {
                        ImGui.endTabItem();
                    }
                    ImGui.endTabBar();
                }
                ImGui.end();
            }
        };
        ImGuiRenderer.INSTANCE.draw(runnable);
    }


}
