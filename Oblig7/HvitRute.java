class HvitRute extends Rute{
  public HvitRute(int radKord,int kolKord,Labyrint hvilkenLabyrint){
    super(radKord,kolKord,hvilkenLabyrint);
  }
  @Override
  public char tilTegn(){
    return ' ';            //returnere mellomrom for hvit rute.
  }
}
