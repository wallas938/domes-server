package fr.greta.domes_server.enums;

public enum Category {
    DOG() {
        public String getCategoryName() {
            return "DOG";
        }
    },
    FISH() {
        public String getCategoryName() {
            return "FISH";
        }
    },
    REPTILE() {
        public String getCategoryName() {
            return "REPTILE";
        }
    },
    BIRD() {
        public String getCategoryName() {
            return "BIRD";
        }
    },
    CAT() {
        public String getCategoryName() {
            return "CAT";
        }
    }
}
