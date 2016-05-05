package GUI.model;

import GUI.controller.historic.ActionPanel;
import GUI.controller.historic.HistoricController;
import GUI.controller.panel.ImagePanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;

@SuppressWarnings("unchecked")
public class MainModel extends Observable {

    public JPanel mainPanel;
    public JTabbedPane panelDraw;
    public JLabel statusBar;
    public ArrayList<HistoricController> historic;
    public JList historicList;
    public ArrayList<Thread> filterThread = new ArrayList<Thread>();

    private MainModel() {
        this.historic = new ArrayList<HistoricController>();
        this.historicList = new JList<>();
        this.mainPanel = new JPanel();
        this.panelDraw = new JTabbedPane();
        this.statusBar = new JLabel("Welcome to myphotoshop :)");
        addListeners();
    }

    private static MainModel INSTANCE = new MainModel();

    public static MainModel getInstance() {
        return INSTANCE;
    }

    public ImagePanel getImg() {
        return (ImagePanel) ((JPanel) this.panelDraw.getSelectedComponent()).getComponents()[0];
    }

    public void setImg(BufferedImage img, ActionPanel action) {
        if (getHistoric().isEmpty()) return;
        if (this.panelDraw.getTabCount() <= 0) return;
        getHistoric().add(action);

        setPrivateImg(img);
    }

    @SuppressWarnings( "deprecation" )
    public void cancelFilter() {
        if (filterThread.isEmpty()) return;
        this.filterThread.get(this.filterThread.size() - 1).stop();
        this.filterThread.remove(this.filterThread.size() - 1);
    }

    private void setPrivateImg(BufferedImage img) {
        if (getHistoric().isEmpty()) return;
        if (this.panelDraw.getTabCount() <= 0) return;
        getImg().setImage(img);
        notifyObservers();
        setChanged();

        statusBar.setText(getHistoric().getLastHistoricName());
        setHistoric();
    }

    private void addListeners() {
        panelDraw.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (panelDraw.getTabCount() <= 0) {
                    statusBar.setText("");
                    historicList.setListData(new Vector());
                    return;
                }
                statusBar.setText(getHistoric().getLastHistoricName());
                setHistoric();
            }
        });


        historicList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (historicList.getSelectedIndex() > getHistoric().getCurrentId()) {
                    redo(historicList.getSelectedIndex());
                } else if (historicList.getSelectedIndex() < getHistoric().getCurrentId())
                    undo(historicList.getSelectedIndex());
                if (getHistoric() != null) {
                    historicList.setSelectedIndex(getHistoric().getCurrentId());
                    getImg().repaint();
                }
            }
        });

        historicList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    Thread t = new Thread() {
                        public void run() {
                            historicList.repaint();
                        }
                    };
                    t.run();
                }
            }
        });
    }

    //HISTORIC
    public void deleteHistoric() {
        if (historic.size() <= 0) return;
        historic.remove(panelDraw.getSelectedIndex());
    }

    public void deleteAllHistoric() {
        this.historic = new ArrayList<HistoricController>();
    }

    private void setHistoric() {
        Vector actions = getHistoric().getActionsNames();
        historicList.setListData(actions);
        historicList.setSelectedIndex(getHistoric().getCurrentId());
    }

    public void undo() {
        if (this.panelDraw.getTabCount() <= 0) return;

        if (getHistoric().isEmpty()) return;
        getHistoric().undo();
        setPrivateImg(getHistoric().getHistoricImage());

        if (!getHistoric().isLast())
            setStatusBar("Undo to " + getStatusBar());
    }

    public void redo() {
        if (this.panelDraw.getTabCount() <= 0) return;
        getHistoric().redo();
        setPrivateImg(getHistoric().getHistoricImage());

        if (!getHistoric().isLast())
            setStatusBar("Redo to " + getStatusBar());
    }

    public HistoricController getHistoric() {
        for (HistoricController historicController : this.historic) {
            if (historicController.id == panelDraw.getSelectedIndex()) {
                return historicController;
            }
        }
        this.historic.add(new HistoricController(panelDraw.getSelectedIndex(), getImg().getImage()));
        return this.historic.get(this.historic.size() - 1);
    }

    public void setHistoric(HistoricController historic) {
        this.historic.add(historic);
        historic.setId(this.historic.size() - 1);
    }

    private void undo(int i) {
        if (this.panelDraw.getTabCount() <= 0) return;
        if (getHistoric().isEmpty()) return;
        int j = 0;
        i = getHistoric().getCurrentId() - i;
        while (j < i) {
            getHistoric().undo();
            j++;
        }
        setPrivateImg(getHistoric().getHistoricImage());

        if (!getHistoric().isLast())
            setStatusBar("Undo to " + getStatusBar());
    }

    private void redo(int i) {
        if (this.panelDraw.getTabCount() <= 0) return;
        if (getHistoric().isEmpty()) return;
        int j = 0;
        i = i - getHistoric().getCurrentId();
        while (j < i) {
            getHistoric().redo();
            j++;
        }
        setPrivateImg(getHistoric().getHistoricImage());
        if (!getHistoric().isLast())
            setStatusBar("Redo to " + getStatusBar());
    }


    //STATUS
    public void setStatusBar(String text) {
        this.statusBar.setText(text);
    }

    private String getStatusBar() {
        return this.statusBar.getText();
    }

}