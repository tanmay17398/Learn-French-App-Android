package com.example.shaurya.learnfrench;


public class ListItem {

    private String frenchTranslation;
    private String englishTranslation;

    private final static int NO_IMAGE=-1;
    private int imageResourceId =NO_IMAGE;

    private int audioResourceId;
    /*
    * @param defaultmessage for getting french text view
    *
    * @param englishmessage for getting english text view
    *
    * @param audioId for getting id of the audio required to be played
    *
    * */

    public ListItem(String defaultmessage,String englishmessage,int audioId){
        frenchTranslation=defaultmessage;
        englishTranslation=englishmessage;
        audioResourceId=audioId;
    }

    /*
   * @param defaultmessage for getting french text view
   *
   * @param englishmessage for getting english text view
   *
   *  @param resourceId for getting an imageView
   *  @param audioId for getting id of the audio required to be played
   * */
    public ListItem(String defaultmessage,String englishmessage,int resourceId,int audioId){
        frenchTranslation=defaultmessage;
        englishTranslation=englishmessage;
        imageResourceId=resourceId;
        audioResourceId=audioId;
    }

    public String getFrenchTranslation() {
        return frenchTranslation;
    }

    public String getEnglishTranslation() {

        return englishTranslation;
    }

    /*
    * @return the image ID required to be display*/
    public int getImageResourceId(){
        return imageResourceId;
    }

    /*
    * @return the audio ID required to be played*/
    public int getAudioResourceId(){
        return audioResourceId;
    }

    /*
    * @return whether an image is required to be dispalyed or not
    * return true if image is available otherwise false
    */
    public boolean hasImage(){
        return imageResourceId != NO_IMAGE;
    }
}

