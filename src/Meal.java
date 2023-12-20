class Meal {
    private String[] ingredients;
    private String name;
    protected final String category;

    public Meal(String name, String[] ingredients, String category) {
        this.name = name;
        this.ingredients = ingredients;
        this.category = category;
    }

}

class Breakfast extends Meal {
    public Breakfast(String name, String[] ingredients) {
        super(name, ingredients, "breakfast");
    }
}

class Lunch extends Meal {
    public Lunch(String name, String[] ingredients) {
        super(name, ingredients, "lunch");
    }
}

class Dinner extends Meal {
    public Dinner(String name, String[] ingredients) {
        super(name, ingredients, "dinner");
    }
}

class Snack extends Meal {
    public Snack(String name, String[] ingredients){
        super(name, ingredients, "snack");
    }

}