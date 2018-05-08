package com.magicalrice.adolph.kmovie.data.entities;

import com.google.gson.JsonElement;

import java.util.List;

public class Changes {

    public static class Change {

        private String id;
        private String action;
        private String time;
        private String iso_639_1;
        private JsonElement value;
        private JsonElement original_value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public JsonElement getValue() {
            return value;
        }

        public void setValue(JsonElement value) {
            this.value = value;
        }

        public JsonElement getOriginal_value() {
            return original_value;
        }

        public void setOriginal_value(JsonElement original_value) {
            this.original_value = original_value;
        }
    }

    public static class Entries {
        private String key;
        private List<Change> items;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<Change> getItems() {
            return items;
        }

        public void setItems(List<Change> items) {
            this.items = items;
        }
    }

    private List<Entries> changes;

    public List<Entries> getChanges() {
        return changes;
    }

    public void setChanges(List<Entries> changes) {
        this.changes = changes;
    }
}
