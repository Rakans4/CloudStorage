package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id ="nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "addNewNote-button")
    private WebElement addNewNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitleField;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;

    @FindBy(id = "save-note-button")
    private WebElement saveNoteButton;

    @FindBy(className = "note-title")
    private WebElement noteTitle;

    @FindBy(className = "note-description")
    private WebElement noteDescription;

    @FindBy(className = "edit-note")
    private WebElement editNoteButton;

    @FindBy(className = "delete-note")
    private WebElement deleteNoteButton;

    @FindBy(id = "notes-table-body")
    private WebElement notesTableBody;

    @FindBy(id ="nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "addNewCredentials-button")
    private WebElement addNewCredentialsButton;

    @FindBy(id = "credential-url")
    private WebElement credentialURLField;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameField;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordField;

    @FindBy(id = "save-credentials-button")
    private WebElement saveCredentialsButton;

    @FindBy(className = "credentials-url")
    private WebElement credentialsURL;

    @FindBy(className = "credentials-username")
    private WebElement credentialsUsername;

    @FindBy(className = "credentials-password")
    private WebElement credentialsPassword;

    @FindBy(className = "edit-credentials")
    private WebElement editCredentialsButton;

    @FindBy(className = "delete-credentials")
    private WebElement deleteCredentialsButton;

    @FindBy(id = "credentials-table-body")
    private WebElement credentialsTableBody;

    @FindBy(id = "logout")
    private WebElement logoutButton;

    public HomePage (WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void addNote(String title, String description) {
        notesTab.click();
        addNewNoteButton.click();
        noteTitleField.sendKeys(title);
        noteDescriptionField.sendKeys(description);
        saveNoteButton.click();
    }

    public void editNote(String title, String description) {
        editNoteButton.click();
        noteTitleField.clear();
        noteTitleField.sendKeys(title);
        noteDescriptionField.clear();
        noteDescriptionField.sendKeys(description);
        saveNoteButton.click();
    }

    public void deleteNote() {
        deleteNoteButton.click();
    }

    public Note getNote() {
        Note note = null;
        if(!noteTitle.getText().equals("")) {
            note = new Note();
            note.setNotetitle(noteTitle.getText());
            note.setNotedescription(noteDescription.getText());
        }
        return note;
    }

    public boolean isNotesTableEmpty() {
        return notesTableBody.getText().equals("");
    }

    public void goToNotes() {
        this.notesTab.click();
    }

    public void goToCredentials() { this.credentialsTab.click(); }

    public void addCredentials(String url, String username, String password) {
        credentialsTab.click();
        addNewCredentialsButton.click();
        credentialURLField.sendKeys(url);
        credentialUsernameField.sendKeys(username);
        credentialPasswordField.sendKeys(password);
        saveCredentialsButton.click();
    }

    public void editCredentials(String url, String username, String password) {
        credentialsTab.click();
        editCredentialsButton.click();
        credentialURLField.clear();
        credentialUsernameField.clear();
        credentialPasswordField.clear();
        credentialURLField.sendKeys(url);
        credentialUsernameField.sendKeys(username);
        credentialPasswordField.sendKeys(password);
        saveCredentialsButton.click();
    }

    public void deleteCredentials() {
        deleteCredentialsButton.click();
    }

    public boolean isCredentialsTableEmpty() {
        return this.credentialsTableBody.getText().equals("");
    }

    public Credentials getCredentials() {
        Credentials credentials = null;
        if(!credentialsURL.getText().equals("")) {
            credentials = new Credentials();
            credentials.setUrl(credentialsURL.getText());
            credentials.setUsername(credentialsUsername.getText());
            credentials.setPassword(credentialsPassword.getText());
        }
        return credentials;
    }

    public void logout() {
        logoutButton.click();
    }
}
