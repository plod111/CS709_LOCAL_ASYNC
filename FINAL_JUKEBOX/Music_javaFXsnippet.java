    /*
     * Creating a method that will allow music to play and pause in app through use
     * of CheckBox,
     * if box is ticked music plays, else music is paused.
     * CONTAINS BUG(box needs to be ticked, unticked and ticked again before music
     * launches)
     */
    public void playMusic() {

        Object resource = getClass().getResource("bensound-thousand.mp3");
        Media track = new Media(resource.toString());
        MediaPlayer musicPlayer = new MediaPlayer(track);

        EventHandler<ActionEvent> playMusic = e -> {
            // if (music.isSelected()) {
                musicPlayer.play();
            // } else {
            //     musicPlayer.pause();
            // }
        };

        // music.setOnAction(playMusic);

    }
