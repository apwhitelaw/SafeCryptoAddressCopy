// The intent of this application is to detect a cryptocurrency address on the clipboard and
// make sure that the address is never changed or edited in any way, therefore preventing the
// possibility of another program (malware) accessing the clipboard and inserting a different
// address, causing the user to lose their funds.

package clipboardtracker;


import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;

public class AddressTracker extends Thread implements ClipboardOwner {

    public static final String BITCOIN_REGEX = "^[13][a-km-zA-HJ-NP-Z1-9]{25,34}$";
    public static final Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();

    String previousAddress = "";

    public void run() {
        Transferable trans = cb.getContents(this);
        regainOwnership(trans);
        System.out.println("Listening to board...");
        while(true) {}
    }


    public void lostOwnership(Clipboard c, Transferable t) {
        try {
            this.sleep(20);
        } catch(Exception e) {
            System.out.println("Exception: " + e);
        }

        // If clipboard String was a Bitcoin address when ownership
        // was lost, save the address, else reset the variable
        String previousClip = transferableToString(t);
        if(isBitcoinAddress(previousClip)) {
            previousAddress = previousClip;
        } else {
            previousAddress = "";
        }

        Transferable contents = cb.getContents(this);
        processContents(contents);
        regainOwnership(contents);
    }

    // If new clipboard String is a Bitcoin address, and
    // previousAddress is not empty, check if they are equal
    public void processContents(Transferable t) {
        String s = transferableToString(t);
        if(isBitcoinAddress(s) && !previousAddress.equals("")) {
            if (transferableToString(t).equals(previousAddress)) {
                System.out.println("same address");
            } else {
                System.out.println("ADDRESS CHANGED");
                JOptionPane.showMessageDialog(null, "BITCOIN ADDRESS WAS ALTERED!");
            }
        }
    }

    public void regainOwnership(Transferable t) {
        cb.setContents(t, this);
    }

    // Convert Transferable to String
    public String transferableToString(Transferable t) {
        String s = "";
        try {
            s = t.getTransferData(DataFlavor.stringFlavor).toString();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        if(s.equals("")) {
            return "FAILED TO GET STRING";
        } else {
            return s;
        }
    }

    // Check for a valid Bitcoin address
    public boolean isBitcoinAddress(String s) {
        return s.matches(BITCOIN_REGEX);
    }

    public static void main(String[] args) {
        AddressTracker b = new AddressTracker();
        b.run();
    }

}
