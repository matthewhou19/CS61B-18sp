public interface Deque<Item> {
    public int size();
    public default boolean isEmpty() {
        return size() == 0;
    }
    public void  addFirst(Item item);
    public void addLast(Item item);
    public Item removeFirst();
    public Item removeLast();
    public Item get(int index);
    public void printDeque();



}
