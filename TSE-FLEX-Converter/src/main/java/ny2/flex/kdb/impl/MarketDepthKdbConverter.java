package ny2.flex.kdb.impl;

import java.util.List;

import ny2.flex.data.Data;
import ny2.flex.data.MarketDepth;
import ny2.flex.kdb.KdbConverter;

import com.exxeleron.qjava.QTimespan;

public class MarketDepthKdbConverter implements KdbConverter {

    // //////////////////////////////////////
    // Field
    // //////////////////////////////////////

    private static final String TABLE_NAME = "MarketDepth";

    // //////////////////////////////////////
    // Constructor
    // //////////////////////////////////////

    // //////////////////////////////////////
    // Method
    // //////////////////////////////////////

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Data> Object[] convert(List<T> dataList) {
        return convertInternal((List<MarketDepth>) dataList);
    }

    public Object[] convertInternal(List<MarketDepth> dataList) {
        int rowCount = dataList.size();
        KdbMarketDepth kdbData = new KdbMarketDepth(rowCount);
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            kdbData.addData(rowIndex, (MarketDepth) dataList.get(rowIndex));
        }
        return kdbData.toKdbDataObject();
    }

    @Override
    public Object[] convert(Data data) {
        KdbMarketDepth kdbData = new KdbMarketDepth(1);
        kdbData.addData(0, (MarketDepth) data);
        return kdbData.toKdbDataObject();
    }

    // //////////////////////////////////////
    // Inner Class
    // //////////////////////////////////////

    //
    //    c            | t f a
    //    -------------| -----
    //    time         | n
    //    sym          | s
    //    bidPrice     | f
    //    askPrice     | f
    //    bidQuantity  | j
    //    askQuantity  | j
    //    bidNumOrder  | j
    //    askNumOrder  | j
    //    bidPrices    |
    //    askPrices    |
    //    bidQuantities|
    //    askQuantities|
    //    bidNumOrders |
    //    askNumOrders |
    //    updateType   | s
    //    updateNo     | i
    //    serialNo     | j
    //
    private class KdbMarketDepth {
        // head
        QTimespan[] timeArray;
        String[] symArray;
        // Best
        double[] bidPriceArray;
        double[] askPriceArray;
        long[] bidQuantityArray;
        long[] askQuantityArray;
        long[] bidNumOrderArray;
        long[] askNumOrderArray;
        // Depth
        Object[] bidPricesArray;
        Object[] askPricesArray;
        Object[] bidQuantitiesArray;
        Object[] askQuantitiesArray;
        Object[] bidNumOrdersArray;
        Object[] askNumOrdersArray;
        // Others
        String[] updateTypeArray;
        int[] updateNoArray;
        long[] serialNoArray;

        public KdbMarketDepth(int rowCount) {
            // head
            timeArray = new QTimespan[rowCount];
            symArray = new String[rowCount];
            // Best
            bidPriceArray = new double[rowCount];
            askPriceArray = new double[rowCount];
            bidQuantityArray = new long[rowCount];
            askQuantityArray = new long[rowCount];
            bidNumOrderArray = new long[rowCount];
            askNumOrderArray = new long[rowCount];
            // Depth
            bidPricesArray = new Object[rowCount];
            askPricesArray = new Object[rowCount];
            bidQuantitiesArray = new Object[rowCount];
            askQuantitiesArray = new Object[rowCount];
            bidNumOrdersArray = new Object[rowCount];
            askNumOrdersArray = new Object[rowCount];
            // Others
            updateTypeArray = new String[rowCount];
            updateNoArray = new int[rowCount];
            serialNoArray = new long[rowCount];
        }

        public void addData(int rowIndex, MarketDepth data) {
            // head
            timeArray[rowIndex] = KdbConverter.kdbValueTimespan(data.getTime());
            symArray[rowIndex] = KdbConverter.kdbValue(data.getSym());
            // Best
            bidPriceArray[rowIndex] = data.getBidPrice();
            askPriceArray[rowIndex] = data.getAskPrice();
            bidQuantityArray[rowIndex] = data.getBidQuantity();
            askQuantityArray[rowIndex] = data.getAskQuantity();
            bidNumOrderArray[rowIndex] = data.getBidNumOrder();
            askNumOrderArray[rowIndex] = data.getAskNumOrder();
            // Depth
            bidPricesArray[rowIndex] = data.getBidPrices();
            askPricesArray[rowIndex] = data.getAskPrices();
            bidQuantitiesArray[rowIndex] = data.getBidQuantities();
            askQuantitiesArray[rowIndex] = data.getAskQuantities();
            bidNumOrdersArray[rowIndex] = data.getBidNumOrders();
            askNumOrdersArray[rowIndex] = data.getAskNumOrders();
            // Others
            updateTypeArray[rowIndex] = KdbConverter.kdbValue(data.getUpdateType());
            updateNoArray[rowIndex] = data.getUpdateNo();
            serialNoArray[rowIndex] = data.getSerialNo();
        }

        public Object[] toKdbDataObject() {
            return new Object[] {
                    // head
                    timeArray,
                    symArray,
                    // Best
                    bidPriceArray,
                    askPriceArray,
                    bidQuantityArray,
                    askQuantityArray,
                    bidNumOrderArray,
                    askNumOrderArray,
                    // Depth
                    bidPricesArray,
                    askPricesArray,
                    bidQuantitiesArray,
                    askQuantitiesArray,
                    bidNumOrdersArray,
                    askNumOrdersArray,
                    // Others
                    updateTypeArray,
                    updateNoArray,
                    serialNoArray };
        }
    }
}
