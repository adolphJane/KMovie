package com.magicalrice.adolph.kmovie.data.entities;

public class ChangeResultsPage extends BaseResultsPage<ChangeResultsPage.Change> {

    public static class Change {
        private int id;
        private boolean adult;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }
    }

}
