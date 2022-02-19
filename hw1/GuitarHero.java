import synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    //private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static final double[] CONCERTS = new double[37];

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        //synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
        //synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);
        for (int i = 0; i < 37; i++) {
            CONCERTS[i] = CONCERT_A * Math.pow(2, (i - 24) / 12);
        }
        GuitarString[] GSS = new GuitarString[37];
        for (int i = 0; i < 37; i++) {
            GSS[i] = new GuitarString(CONCERTS[i]);
        }

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        System.out.print(keyboard.length());
        // char[] keys = keyboard.toCharArray();
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                GSS[index].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (GuitarString gs : GSS) {
                sample = gs.sample() + sample;
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString gs : GSS) {
                gs.tic();
            }
        }
    }
}
