


import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    public static final double ROOT_ULLAT = 37.892195547244356, ROOT_ULLON = -122.2998046875,
            ROOT_LRLAT = 37.82280243352756, ROOT_LRLON = -122.2119140625;
    /** Each tile is 256x256 pixels. */
    public static final int TILE_SIZE = 256;

    public static final int largestDepth = 7;

    private static final String[] REQUIRED_RASTER_RESULT_PARAMS = {"depth", "raster_ul_lon", "raster_lr_lon",
            "raster_lr_lat", "raster_ul_lat", "render_grid", "query_success"};

    public Rasterer() {
        // YOUR CODE HERE
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        System.out.println(params);

        // initiate the results as query_success = false;
        Map<String, Object> results = new HashMap<>();
        results.put(REQUIRED_RASTER_RESULT_PARAMS[0], 0);
        results.put(REQUIRED_RASTER_RESULT_PARAMS[1], 0.0);
        results.put(REQUIRED_RASTER_RESULT_PARAMS[2], 0.0);
        results.put(REQUIRED_RASTER_RESULT_PARAMS[3], 0.0);
        results.put(REQUIRED_RASTER_RESULT_PARAMS[4], 0.0);
        results.put(REQUIRED_RASTER_RESULT_PARAMS[5], null);
        results.put(REQUIRED_RASTER_RESULT_PARAMS[6], false);

        // 1. check full parameter
        if (!checkFullPara(params)) {
            return results;
        }


        Double lrlon = params.get("lrlon");
        Double ullon = params.get("ullon");
        Double w = params.get("w");
        Double h = params.get("h");
        Double ullat = params.get("ullat");
        Double lrlat = params.get("lrlat");

        // 1. check if the data is validated
        if (!validation(lrlon, ullon, ullat, lrlat, w , h)) {
            return results;
        }

        // 2. decide the depth
        double queryLonDDP = lonDDP(lrlon, ullon, w);
        int d  = decideDepth(queryLonDDP);

        // 3. build the grid
        // 3.1 find the upper left node
        // index 0 is longitude, index 1 is latitude
        int[] upperLeft = findNode(ullon, ullat, d);
        // 3.2 find the lower right node
        int[] lowerRight = findNode(lrlon, lrlat, d );
        // 3.3 build the grid
        /**
        System.out.println(d);
        System.out.println(upperLeft[0]);
        System.out.println(upperLeft[1]);

        System.out.println(lowerRight[0]);
        System.out.println(lowerRight[1]); */

        String[][] grid = buildGrid(upperLeft, lowerRight, d);
        // 4. return the data
        double ul_lon = calcLonByNode(upperLeft[0], d)[0];
        double ul_lat = calcLatByNode(upperLeft[1], d)[0];
        double lr_lon = calcLonByNode(lowerRight[0], d)[1];
        double lr_lat = calcLatByNode(lowerRight[1], d)[1];



        results.put(REQUIRED_RASTER_RESULT_PARAMS[0], d);
        results.put(REQUIRED_RASTER_RESULT_PARAMS[1], ul_lon);
        results.put(REQUIRED_RASTER_RESULT_PARAMS[2], lr_lon);
        results.put(REQUIRED_RASTER_RESULT_PARAMS[3], lr_lat);
        results.put(REQUIRED_RASTER_RESULT_PARAMS[4], ul_lat);
        results.put(REQUIRED_RASTER_RESULT_PARAMS[5], grid);
        results.put(REQUIRED_RASTER_RESULT_PARAMS[6], true);


        //System.out.println("Since you haven't implemented getMapRaster, nothing is displayed in "
                         //  + "your browser.");
        return results;
    }


    // check if all params exist
    private boolean checkFullPara(Map<String, Double> params) {
        if (!params.keySet().contains("lrlon")) {
            return false;
        }

        if (!params.keySet().contains("ullon")) {
            return false;
        }
        if (!params.keySet().contains("w")) {
            return false;
        }

        if (!params.keySet().contains("h")) {
            return false;
        }

        if (!params.keySet().contains("ullat")) {
            return false;
        }

        if (!params.keySet().contains("lrlat")) {
            return false;
        }

        return true;
    }

    // check if all params validated
    private boolean validation(Double lrlon, Double ullon, Double ullat, Double lrlat, Double w, Double h) {
        if (lrlon == null || ullon == null || ullat == null || lrlat == null || w == null || h == null) {
            return false;
        }

        if (lrlat >= ullat || lrlon <= ullon) {
            return false;
        }

        // check left right corner
        if (lrlat >= ROOT_ULLAT || lrlon <= ROOT_ULLON) {
            return false;
        }

        // check upper left corner
        if (ullat <= ROOT_LRLAT || ullon >= ROOT_LRLON) {
            return false;
        }
        return true;
    }

    // units of longitude per pixel.
    private double lonDDP(double lrlon, double ullon, double w) {
        return (lrlon - ullon) / w;
    }

    // find the proper depth
    private int decideDepth(double queryLonDDp) {
        int d = 0;
        double rootLonDDp = lonDDP(ROOT_LRLON, ROOT_ULLON, TILE_SIZE);
        while (d < 7) {
            double LonDDp = rootLonDDp / (Math.pow(2, d));
            if (LonDDp > queryLonDDp) {
                d++;
            } else {
                break;
            }
        }
        return d;
    }
    // find the node index of depth
    private int[] findNode(Double lon, Double lat, int depth) {
        int[] res = new int[] {0, 0}; // index 0 is longitude, index 1 is latitude
        int lagestIndex = (int) (Math.pow(2, depth)) - 1;
        res[0] = findLongIndex(lon, depth, lagestIndex);
        res[1] = findLatIndex(lat, depth, lagestIndex);
        return res;
    }

    // find node longitude index of depth
    private int findLongIndex(Double lon, int depth, int lagestIndex) {
        if (lon <= ROOT_ULLON) return 0;
        if (lon >= ROOT_LRLON) return lagestIndex;
        int left = 0;
        int right = lagestIndex;
        while (right > left) {
            int mid = (left + right) / 2;
            double[] midNode = calcLonByNode(mid, depth);
            if (lon >= midNode[0] && lon < midNode[1]) return mid;
            if (lon < midNode[0]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    // find node latitude index of depth
    private int findLatIndex(Double lat, int depth, int largestIndex) {
        if (lat <= ROOT_LRLAT) return largestIndex;
        if (lat >= ROOT_ULLAT) return 0;
        int up = 0;
        int low = largestIndex;
        while (low > up) {
            int mid = (low + up) / 2;
            double[] midNode = calcLatByNode(mid, depth);
            if (lat<= midNode[0] && lat > midNode[1]) return mid;
            if (lat > midNode[0]) {
                low = mid - 1;
            } else {
                up = mid + 1;
            }
        }
        return low;
    }


    private double[] calcLonByNode(int index, int depth) {
        int numNode = (int) (Math.pow(2, depth));
        double nodeLon = (ROOT_LRLON - ROOT_ULLON) / numNode;

        double[] res = new double[2];
        res[0] = ROOT_ULLON + index * nodeLon;
        res[1] = ROOT_ULLON + (index + 1) * nodeLon;
        return res;
    }

    private double[] calcLatByNode(int index, int depth) {
        int numNode = (int) (Math.pow(2, depth));
        double nodeLat = (ROOT_LRLAT - ROOT_ULLAT) / numNode;
        double[] res = new double[2];
        res[0] = ROOT_ULLAT + index * nodeLat;
        res[1] = ROOT_ULLAT + (index + 1) * nodeLat;
        return res;
    }

    private String[][] buildGrid(int[] upperLeft, int[] lowerRight, int depth) {
        int row = lowerRight[1] - upperLeft[1] + 1;
        int col = lowerRight[0] - upperLeft[0] + 1;
        String[][] grid = new String[row][col];
        int lonStart = upperLeft[0];
        int latStart = upperLeft[1];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = generateMapString(lonStart + j,latStart + i,  depth);
            }
        }
        return grid;
    }

    private String generateMapString(int row, int col, int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append('d');
        sb.append(depth);
        sb.append("_x");
        sb.append(row);
        sb.append("_y");
        sb.append(col);
        sb.append(".png");
        return sb.toString();
    }






}
