package model;

public enum Category{
    B(1), L(2), D(3), S(4);

    public final int dbCategory;
    Category(int dbCategory){
        this.dbCategory = dbCategory;
    }

    public int getDbCategory(){
        return dbCategory;
    }
}
