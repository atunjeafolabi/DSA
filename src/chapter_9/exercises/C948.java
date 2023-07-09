package chapter_9.exercises;

import chapter_9.heap.HeapPriorityQueue;
import chapter_9.heap.MaxHeap;

import java.math.BigDecimal;

public class C948 {

    private class Order {
        private BigDecimal unitPrice;
        private int quantity;
        private String type;

        private Order(String type, BigDecimal unitPrice, int quantity) {
            this.type = type;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getType() {
            return type;
        }
    }

    private MaxHeap<BigDecimal, Order> buyOrders = new MaxHeap<>();
    private HeapPriorityQueue<BigDecimal, Order> sellOrders = new HeapPriorityQueue();

    public void placeOrder(String type, BigDecimal unitPrice, int quantity) {
        Order newOrder = new Order(type, unitPrice, quantity);

        if (type.equals("buy")) {
            buyOrders.insert(unitPrice, newOrder);
        } else {
            sellOrders.insert(unitPrice, newOrder);
        }
    }

    public void process(Order order) {
        if (canBeProcessed(order)) {
            // process order
            // i.e reduce order from sellOrders and buyOrders
        }
    }

    private boolean canBeProcessed(Order order) {
        /*
         * if order is BUY ...
         * check if there is an existing SELL order
         * available in the heap to handle the BUY order
         */
        if (order.getType().equals("buy")) {
            BigDecimal x = order.getUnitPrice();
            BigDecimal y = sellOrders.min().getValue().getUnitPrice();

            if (x.compareTo(y) >= 0) {
                // can be processed
                return true;
            }
        } else {
            /*
             * if order is SELL ... check to see if there is any BUY
             * order available in the heap that can be handled.
             */
            BigDecimal x = buyOrders.max().getValue().getUnitPrice();
            BigDecimal y = order.getUnitPrice();

            if (x.compareTo(y) >= 0) {
                // can be processed
                return true;
            }
        }

        return false;
    }
}
