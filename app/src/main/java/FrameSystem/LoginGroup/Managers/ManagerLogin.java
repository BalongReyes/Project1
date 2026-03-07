package FrameSystem.LoginGroup.Managers;

import java.sql.SQLException;

import ConsoleSystem.Console;
import DatabaseSystem.AccountsData.AccountsDataTable;
import DatabaseSystem.SettingsData.SettingsDataHandler;
import DatabaseSystem.SettingsData.SettingsDataTable;
import FrameSystem.HeroGroup.Components.HeroLayer;
import MainSystem.Manager;

public class ManagerLogin extends Manager {

    public static void initDefault() {
        resetUI();
    }

    private static void resetUI() {
    }

// Methods ===================================================================================================
    
    private static AccountsDataTable accountLoggedIn = null;

    public static AccountsDataTable getAccountLoggedIn() {
        return accountLoggedIn;
    }

    public static void updateAccountLoggedIn(AccountsDataTable accountLoggedIn) {
        if (!ManagerLogin.accountLoggedIn.idEquals(accountLoggedIn.getId())) {
            return;
        }
        ManagerLogin.accountLoggedIn = accountLoggedIn;
    }

    public static boolean isLoggedIn() {
        return accountLoggedIn != null;
    }

    public static SettingsDataTable getSettingsData() {
        return getSettingsData(false);
    }

    public static SettingsDataTable getSettingsData(boolean refresh) {
        try {
            return SettingsDataHandler.findDataByAccountId(accountLoggedIn.getId(), refresh);
        } catch (SQLException e) {
            Console.errorOut("Finding settings data error", e);
        }
        return null;
    }

// -----------------------------------------------------------------------------------------------------------
    public static void logoutAccount() {
        accountLoggedIn = null;
        frame.heroMenuPanel.setVisible(false);
        frame.heroMenuPanel1.setVisible(false);
        HeroLayer.showLayer(frame.heroLayer_Login);
//        frame.loginUsernameField.requestFocus();

        frame.stopAutoLogout();
    }

    public static void loginAccount() {
        boolean insufficientInput = false;

//        if (frame.loginUsernameField.getText().isEmpty()) {
//            insufficientInput = true;
//        }
//        frame.loginError1.setVisible(frame.loginUsernameField.getText().isEmpty());
//
//        if (frame.loginPasswordField.getPassword().length == 0) {
//            insufficientInput = true;
//        }
//        frame.loginError2.setVisible(frame.loginPasswordField.getPassword().length == 0);
//
//        if (insufficientInput) {
//            frame.loginError3.setVisible(false);
//            return;
//        }
//
//        AccountsDataTable accountSuccessLogin = null;
//        try {
//            accountSuccessLogin = AccountsDataHandler.loginAccount(frame.loginUsernameField.getText(), frame.loginPasswordField.getPassword());
//        } catch (SQLException e) {
//            Console.errorOut("Login error", e);
//            frame.offlineMode();
//        }
//        if (accountSuccessLogin != null) {
//            accountLoggedIn = accountSuccessLogin;
//
//            SettingsDataTable settingsData = getSettingsData(true);
//            frame.updateAutoLogout(settingsData.getAutoLogoutTime());
//
//            frame.heroMenuPanel.setVisible(true);
//            // HeroLayer.showLayer(frame.heroLayer_ItemOut);
//            resetUI();
//
//            ManagerSideBar.changeAccount(accountLoggedIn);
//        } else {
//            frame.loginError3.setVisible(true);
//        }
    }

}
