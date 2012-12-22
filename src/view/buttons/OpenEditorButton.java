package view.buttons;

import utils.MapBuilder;
import utils.ResourceManager;
import utils.Utils;
import view.editorPanels.EditSidePanel;
import view.mapPanels.MapPanel;

import javax.swing.*;
import java.io.File;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class OpenEditorButton extends EditorButton {

    public OpenEditorButton(int x, int y, EditSidePanel panel, MapPanel mapPanel,
                            MapBuilder mapBuilder, ResourceManager resourceManager) {
        super(x, y, resourceManager.spriteMap.get("EDITOR_OPEN_BUTTON"), panel, mapPanel, mapBuilder);
    }

    @Override
    public void click() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(Utils.getFile(mapBuilder.getDefaultMapFile().getPath()));
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file.canRead()) {
                mapPanel.setGameMap(mapBuilder.buildMap(file));
                panel.setFile(file);
            } else {
                System.err.println("Cannot open file: file cannot be read");
            }
        }
    }
}
