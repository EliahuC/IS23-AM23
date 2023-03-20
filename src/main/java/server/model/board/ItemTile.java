package server.model.board;

import java.util.Objects;

public class ItemTile {
private final Category category;

    public ItemTile(Category category){
        this.category=category;
    }

    public Category getCategory(){
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemTile itemTile = (ItemTile) o;
        return category == itemTile.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
