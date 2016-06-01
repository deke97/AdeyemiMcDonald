/** Name : Abby McDonald and Oluwaseun Adeyemi
  * Pennkey : abigailm
  * Recitation : 202
  * 
  * Execution : java WordCloud imagefile(jpg/png/) audiofile (wav) textfile (.txt)
  */

public class WordCloud {
    
    String word = "";
    int count = 0;
    
    /*
     * Description: //creates an object that has two pieces of data, so now have 
     //both the data in object type Pair as well as its frequency, int count.
     * Input: String of words and integer of count(frequency)
     * Output: pair object
     */
    private class Pair { //creates an object that has two pieces of data, so now have 
        //both the data in object type Pair as well as its frequency, int count.
        String word;
        int count; 
    }
    
    
    /*
     * Description: breaks String of all lyrics down into words and counts each word's frequency
     * Input: String of lyrics 
     * Output: Pairs of sorted word and count data 
     */
    private LinkedList<Pair> readData(String lyricsFile) {
        In inStream = new In(lyricsFile);
        String lyrics = inStream.readAll(); 
        String oneWord = "";
        // Count the words.
        int wordCount = 0;
        for (int i = 0; i < lyrics.length(); i++) {
            if (lyrics.charAt(i) == ' ' || lyrics.charAt(i) == '\t' || lyrics.charAt(i) == '\n') {
                wordCount++;
            }
        }
        
        LinkedList<Pair> wordCloud = new LinkedList<Pair>();
        //wordCloud is a linkedlist containing Pairs
        String[] words = lyrics.replaceAll("\t"," ").replaceAll("\n"," ").split(" ");
        for (int i = 0; i< words.length; i++) {
            String s = words[i]; 
            boolean seen = false;
            for(int x = 0; x < wordCloud.size(); x++){
                Pair p = wordCloud.get(x);
                if(p.word.equals(s)) {
                    p.count++;
                    wordCloud.set(x,p);
                    seen = true;
                    break;
                }
            }
            if (!seen) {
                Pair np = new Pair();
                np.word = s;
                np.count = 1;
                wordCloud.add(np);
            }
        }
        sort(wordCloud);
        return wordCloud; 
    }
    
    /*
     * Description: sorts words in descending order of frequency
     * Input: Pairs
     * Output: sorted Pairs
     */
    public static void sort(LinkedList<Pair> counts) {
        int N = counts.size();
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (counts.get(j - 1).count < counts.get(j).count)
                    exch(counts, j - 1, j);
                else break;
            }
        }
    }
    
    /*
     * Description: exchanges words in linked list to put in order
     * Input: Pairs
     * Output: sorted Pairs
     */
    private static void exch(LinkedList<Pair> counts, int i, int j) {
        Pair p1 = counts.get(i);
        Pair p2 = counts.get(j);
        String p11 = p1.word;
        int pairCount1 = p1.count;
        p1.count = p2.count;
        p1.word = p2.word;
        p2.count = pairCount1;
        p2.word = p11;
    }
    
    
    /*
     * Description: draws wordCloud text
     * Input: Linked List of Pairs
     * Output: PennDraw printed text
     */
    private static void drawCloud(LinkedList<Pair> drawn) {
        for (int i = 0; i < drawn.size(); i++) {
            int r = (int) (Math.random() * 255);
            int g = (int) (Math.random() * 255);
            int b = (int) (Math.random() * 255);
            PennDraw.setPenColor(r, g, b);
            if (drawn.get(i).count >= 25) {
                PennDraw.setFontSize(95);
                PennDraw.text(Math.random(), Math.random(), drawn.get(i).word, 
                              Math.random()*360);
            }
            if (drawn.get(i).count >= 20 && drawn.get(i).count < 25) {
                PennDraw.setFontSize(80);
                PennDraw.text(Math.random(), Math.random(), drawn.get(i).word, 
                              Math.random()*360);
            }
            if (drawn.get(i).count >= 15 && drawn.get(i).count < 20) {
                PennDraw.setFontSize(70);
                PennDraw.text(Math.random(), Math.random(), drawn.get(i).word, 
                              Math.random()*360);
            }
            if (drawn.get(i).count >= 10 && drawn.get(i).count < 15) {
                PennDraw.setFontSize(60);
                PennDraw.text(Math.random(), Math.random(), drawn.get(i).word, 
                              Math.random()*360);
            }
            if (drawn.get(i).count >= 7 && drawn.get(i).count < 10) {
                PennDraw.setFontSize(50);
                PennDraw.text(Math.random(), Math.random(), drawn.get(i).word, 
                              Math.random()*360);
            }
            if (drawn.get(i).count >= 4 && drawn.get(i).count < 7) {
                PennDraw.setFontSize(40);
                PennDraw.text(Math.random(), Math.random(), drawn.get(i).word, 
                              Math.random()*360);
            }
            if (drawn.get(i).count >= 1 && drawn.get(i).count < 4) {
                PennDraw.setFontSize(20);
                PennDraw.text(Math.random(), Math.random(), drawn.get(i).word, 
                              Math.random()*360);
            }
        }  
    }
    
    public static void main(String[] args) {
        WordCloud wc = new WordCloud();
        String picFilename = args[0];
        String wavFile = args[1];
        String lyricsFile = args[2];
        PennDraw.picture(0.5, 0.5, picFilename, 500, 500); //draw background
        StdAudio.loop(wavFile); //play music 
        LinkedList<Pair> drawn = wc.readData(lyricsFile);
        drawCloud(drawn);
        while (true) {
          if (PennDraw.mousePressed()) { //interactive component 
            drawCloud(drawn);
          }
        }
    }
}

