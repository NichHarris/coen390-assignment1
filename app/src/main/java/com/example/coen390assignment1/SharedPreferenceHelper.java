package com.example.coen390assignment1;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private SharedPreferences sharedPreferences;
    public  SharedPreferenceHelper(Context context){
        sharedPreferences = context.getSharedPreferences("EventPreference", Context.MODE_PRIVATE);
    }

    // Verify the input max count is within range and add it to the SharedPreference
    public void saveMaxCount(String max){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int maxInt = Integer.parseInt(max);
        assert editor != null;
        if (maxInt > 200){
            editor.putInt("maxCount", 200);
        }
        else if(maxInt < 5){
            editor.putInt("maxCount", 5);
        }
        else{
            editor.putInt("maxCount", maxInt);
        }
        editor.apply();
    }

    // Return the assigned value to maxCount
    public int getMaxCount() {
        return sharedPreferences.getInt("maxCount", 5);
    }

    // Increment the stored value of totalEvents
    public void incrementTotalEvents(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int value = sharedPreferences.getInt("totalEvents", 0);
        value++;

        assert editor != null;
        editor.putInt("totalEvents", value);
        editor.apply();
    }

    // Return the assigned value of totalEvents, or a default of 0 if not set
    public int getTotalEvents(){
        return sharedPreferences.getInt("totalEvents", 0);
    }

    // Save the name of one of the counters
    public void saveEventName(String name, int eventId){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        assert editor != null;
        editor.putString(getEventType(eventId), name);
        editor.apply();
    }

    // Return the name assigned to a counter
    public String getEventName(int eventId){ return sharedPreferences.getString(getEventType(eventId), null); }

    // Increment the store value of a counter
    public void incrementEventValue(int eventId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String eventName = getEventCounterName(eventId);
        int value = sharedPreferences.getInt(eventName, 0 );
        value++;

        assert editor != null;
        editor.putInt(eventName, value);
        editor.apply();
    }

    // Return the assigned value of a counter
    public int getEventValue(int eventId){
        return sharedPreferences.getInt(getEventCounterName(eventId), 0);
    }

    // Returns string of a counters values key
    private String getEventCounterName(int eventId){
        String eventCounterName = null;
        switch (eventId) {
            case 0:
                eventCounterName = "event1";
                break;
            case 1:
                eventCounterName = "event2";
                break;
            case 2:
                eventCounterName = "event3";
                break;
        }
        assert eventCounterName != null;
        return  eventCounterName;
    }

    // Returns the string of a counters names key
    private String getEventType(int eventId){
        String eventName = null;
        switch (eventId){
            case 0:
                eventName = "eventOne";
                break;
            case 1:
                eventName = "eventTwo";
                break;
            case 2:
                eventName = "eventThree";
                break;
        }
        assert eventName != null;
        return eventName;
    }

    // Remove a key/value pair from the SharedPreference
    public void removeData(String key) {
        if(sharedPreferences.contains(key)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            assert editor != null;
            editor.remove(key);
            editor.apply();
        }
    }

    // Set the state for the editMode
    public void setEditMode(boolean enabled){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        assert editor != null;
        editor.putBoolean("editMode", enabled);
        editor.apply();
    }

    // Get the state for the editMode
    public boolean getEditMode() { return sharedPreferences.getBoolean("editMode", true); }

    // Set the state for the dataActivityMode
    public void setDataActivityMode(boolean enabled){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        assert editor != null;
        editor.putBoolean("dataActivityMode", enabled);
        editor.apply();
    }

    // Get the state for the dataActivityMode: FALSE is showing the set counter names, TRUE shows just the counter number
    public boolean getDataActivityMode() { return sharedPreferences.getBoolean("dataActivityMode", true); }

    public String returnName(int eventId) {
        if (getDataActivityMode()) {
            return getEventName(eventId);
        }
        else {
            switch (eventId) {
                case 0:
                    return "1";
                case 1:
                    return "2";
                case 2:
                    return "3";
            }
        }
        return "Error";
    }
}
