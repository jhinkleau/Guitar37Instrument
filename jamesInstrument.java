import java.util.*;

public class jamesInstrument implements Instrument{
    // ArrayList --> guitar strings
    // LinkedList --> the sample frequency buffer
    // Double --> a random number in [-0.5;0.5]
    ArrayList<LinkedList<Double>> strings;

    jamesInstrument()
    {
        strings = new ArrayList<>();
        for (int i=0; i < 37; i++)
        {
            double freq = 440.0 * Math.pow(2.0, (i - 24) / 12.0);
            int size = (int)(44100 / freq);
            LinkedList<Double> ll = new LinkedList<>();
            for(int j = 0; j<size; j++)
            {
                ll.add(0.0);
            }
            strings.add(ll);
        }
    }
        @Override
        public void pluck(char key) {
            double freq = 440.0 * Math.pow(2.0, (key % 37 - 24) / 12.0);
            int size = (int)(44100.0 / freq);
            for (int gs = 0; gs < 37; gs++) {
                for (int f = 0; f < size; f++) {
                    double r = 0.4; // random [-0.5; 0.5]
                    strings.get(gs).set(f, r);
                }
            }
        }
    
        @Override
        public void tick() {
            for (int gs = 0; gs < 37; gs++) {
                LinkedList<Double> buffer = strings.get(gs);
                double magic = 0.994 * (buffer.get(0) + buffer.get(1)) / 2.0;
                buffer.add(magic);
                buffer.remove();
            }
        }
    
        @Override
        public double superposition() {
            // TODO Auto-generated method stub
            double sum = 0;
            for(int i = 0; i < 37; i++)
            {
                LinkedList<Double> buffer = strings.get(i);
                double temp = buffer.get(0);
                sum = sum + temp;
            }
            return sum;
        }
        
    }