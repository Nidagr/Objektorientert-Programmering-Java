class SortRute extends Rute{
  public SortRute(int radKord,int kolKord,Labyrint hvilkenLabyrint){
    super(radKord,kolKord,hvilkenLabyrint);
  }
  @Override
  public char tilTegn(){
    return '#';           //# står for svart rute.
  }
}
