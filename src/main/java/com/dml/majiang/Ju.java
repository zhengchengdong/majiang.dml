package com.dml.majiang;

/**
 * 一局麻将
 * 
 * @author Neo
 *
 */
public class Ju {

	private Pan currentPan;

	/**
	 * 一局打几盘
	 */
	private int panShu;

	private PlayersMenFengDeterminer playersMenFengDeterminerForFirstPan;
	private ZhuangDeterminer zhuangDeterminerForFirstPan;
	private AvaliablePaiFiller avaliablePaiFiller;
	private GuipaiDeterminer guipaiDeterminer;
	private FaPaiStrategy faPaiStrategy;
	private MajiangPlayerInitialActionUpdater initialActionUpdater;
	private MajiangPlayerMoActionProcessor moActionProcessor;
	private MajiangPlayerMoActionUpdater moActionUpdater;

	private int panActionFrameBufferSize;

	public void determinePlayersMenFengForFirstPan() throws Exception {
		playersMenFengDeterminerForFirstPan.determinePlayersMenFeng(this);
	}

	public void determineZhuangForFirstPan() throws Exception {
		zhuangDeterminerForFirstPan.determineZhuang(this);
	}

	public void fillAvaliablePai() throws Exception {
		avaliablePaiFiller.fillAvaliablePai(this);
	}

	public void determineGuipai() throws Exception {
		guipaiDeterminer.determineGuipai(this);
	}

	public void faPai() throws Exception {
		faPaiStrategy.faPai(this);
	}

	public byte[] updateInitialAction() throws Exception {
		initialActionUpdater.updateActions(this);
		return currentPan.recordPanActionFrame(null);
	}

	public byte[] action(String playerId, int actionId) throws Exception {
		MajiangPlayerAction action = currentPan.findPlayerActionCandidate(playerId, actionId);
		if (action == null) {
			throw new MajiangPlayerActionNotFoundException();
		}
		processAction(playerId, action);
		updateAction(playerId, action);
		return currentPan.recordPanActionFrame(action);
	}

	private void processAction(String playerId, MajiangPlayerAction action) throws Exception {

		if (action instanceof MajiangMoAction) {
			moActionProcessor.process(playerId, (MajiangMoAction) action, this);
		} else {
		}

	}

	private void updateAction(String playerId, MajiangPlayerAction action) throws Exception {

		if (action instanceof MajiangMoAction) {
			moActionUpdater.updateActions(playerId, (MajiangMoAction) action, this);
		} else {
		}

	}

	public Pan getCurrentPan() {
		return currentPan;
	}

	public void setCurrentPan(Pan currentPan) {
		this.currentPan = currentPan;
	}

	public int getPanShu() {
		return panShu;
	}

	public void setPanShu(int panShu) {
		this.panShu = panShu;
	}

	public PlayersMenFengDeterminer getPlayersMenFengDeterminerForFirstPan() {
		return playersMenFengDeterminerForFirstPan;
	}

	public void setPlayersMenFengDeterminerForFirstPan(PlayersMenFengDeterminer playersMenFengDeterminerForFirstPan) {
		this.playersMenFengDeterminerForFirstPan = playersMenFengDeterminerForFirstPan;
	}

	public ZhuangDeterminer getZhuangDeterminerForFirstPan() {
		return zhuangDeterminerForFirstPan;
	}

	public void setZhuangDeterminerForFirstPan(ZhuangDeterminer zhuangDeterminerForFirstPan) {
		this.zhuangDeterminerForFirstPan = zhuangDeterminerForFirstPan;
	}

	public AvaliablePaiFiller getAvaliablePaiFiller() {
		return avaliablePaiFiller;
	}

	public void setAvaliablePaiFiller(AvaliablePaiFiller avaliablePaiFiller) {
		this.avaliablePaiFiller = avaliablePaiFiller;
	}

	public GuipaiDeterminer getGuipaiDeterminer() {
		return guipaiDeterminer;
	}

	public void setGuipaiDeterminer(GuipaiDeterminer guipaiDeterminer) {
		this.guipaiDeterminer = guipaiDeterminer;
	}

	public FaPaiStrategy getFaPaiStrategy() {
		return faPaiStrategy;
	}

	public void setFaPaiStrategy(FaPaiStrategy faPaiStrategy) {
		this.faPaiStrategy = faPaiStrategy;
	}

	public MajiangPlayerInitialActionUpdater getInitialActionUpdater() {
		return initialActionUpdater;
	}

	public void setInitialActionUpdater(MajiangPlayerInitialActionUpdater initialActionUpdater) {
		this.initialActionUpdater = initialActionUpdater;
	}

	public MajiangPlayerMoActionProcessor getMoActionProcessor() {
		return moActionProcessor;
	}

	public void setMoActionProcessor(MajiangPlayerMoActionProcessor moActionProcessor) {
		this.moActionProcessor = moActionProcessor;
	}

	public MajiangPlayerMoActionUpdater getMoActionUpdater() {
		return moActionUpdater;
	}

	public void setMoActionUpdater(MajiangPlayerMoActionUpdater moActionUpdater) {
		this.moActionUpdater = moActionUpdater;
	}

	public int getPanActionFrameBufferSize() {
		return panActionFrameBufferSize;
	}

	public void setPanActionFrameBufferSize(int panActionFrameBufferSize) {
		this.panActionFrameBufferSize = panActionFrameBufferSize;
	}

}
