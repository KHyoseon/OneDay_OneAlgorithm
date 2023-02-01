import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int N = genres.length;
        Song[] songs = new Song[N];

        for(int i=0; i<N; i++){
            songs[i] = new Song(i, plays[i], genres[i]);
        }

        HashMap<String, Integer> allGenre = new HashMap<>();
        for (int i = 0; i < N; i++) {
            allGenre.put(genres[i], allGenre.getOrDefault(genres[i], 0) + plays[i]);
        }

        Arrays.sort(songs, (o1, o2) -> {
            if(o1.genre.equals(o2.genre)){
                if(o1.play == o2.play){
                    return o1.id - o2.id;
                }
                return o2.play - o1.play;
            }
            return allGenre.get(o2.genre) - allGenre.get(o1.genre);
        });

        ArrayList<Integer> answer = new ArrayList<>();

        String prevG = "";
        for(int i=0; i<N;){
            // i번째 곡의 장르
            prevG = songs[i].genre;

            for(int j=0; i<N && j<2 && prevG.equals(songs[i].genre); j++) {
                answer.add(songs[i++].id);
            }

            while(i<N && prevG.equals(songs[i].genre)){
                i++;
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}

class Song {
    int id, play;
    String genre;

    public Song(int id, int play, String genre) {
        this.id = id;
        this.play = play;
        this.genre = genre;
    }
}