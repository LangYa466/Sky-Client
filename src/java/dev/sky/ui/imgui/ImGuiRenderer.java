package dev.sky.ui.imgui;

import com.google.common.io.ByteStreams;
import imgui.ImGui;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.lwjgl.opengl.Display;

import java.io.IOException;

public enum ImGuiRenderer {
    INSTANCE;
    private final ImGuiImplGlfw implGlfw = new ImGuiImplGlfw();
    private final ImGuiImplGl3 implGl3 = new ImGuiImplGl3();
    private boolean initialized = false;

    private void initialize() {
        if (!initialized) {
            ImGui.createContext();
            try {
                ImGui.getIO().getFonts().addFontFromMemoryTTF(ByteStreams.toByteArray(this.getClass().getResourceAsStream("/assets/minecraft/skyclient/fonts/PingFang.ttf")), 30, ImGui.getIO().getFonts().getGlyphRangesChineseSimplifiedCommon());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //ImGui.getIO().getFonts().addFontFromFileTTF("c:/windows/fonts/arial.ttf", 20);
            implGlfw.init(Display.getWindow(), true);
            implGl3.init();
            initialized = true;
        }
    }
    public void draw(final Runnable consumer) {
        if (!initialized) initialize();
        implGlfw.newFrame();
        ImGui.newFrame();
        consumer.run();
        ImGui.render();
        implGl3.renderDrawData(ImGui.getDrawData());
    }
}