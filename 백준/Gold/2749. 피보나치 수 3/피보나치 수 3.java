import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Long N = Long.parseLong(br.readLine());
        int p = 15*(int)Math.pow(10, 5);
        N %= p;

        int Fn1 = 0;
        int Fn2 = 1;
        int Fn = 0;
        int cnt = 0;
        while(cnt<N){
            Fn = (Fn1+Fn2)%1000000;
            Fn2 = Fn1;
            Fn1 = Fn;
            cnt++;
        }
        bw.write(Fn+"\n");
        bw.flush();
        bw.close();
    }
}