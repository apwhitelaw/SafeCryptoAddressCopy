// Creates a tray icon for this applicaiton so the user
// can see it is running and exit when finished

package clipboardtracker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTrayIcon {

    public void setupTrayIcon() {
        TrayIcon trayIcon = null;

        if(SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(MyTrayIcon.class.getResource("/images/trayicon.png"));

            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            };

            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("Quit");
            defaultItem.addActionListener(listener);
            popup.add(defaultItem);

            trayIcon = new TrayIcon(image, "SafeCryptoAddressCopy", popup);
            trayIcon.setImageAutoSize(true);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
        } else {
            System.out.println("SystemTray not supported");
        }


    }

}
