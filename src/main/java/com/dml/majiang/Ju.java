package com.dml.majiang;

import java.util.ArrayList;
import java.util.List;

/**
 * 一局麻将
 * 
 * @author Neo
 *
 */
public class Ju {

	private Pan currentPan;

	private List<PanResult> finishedPanResultList = new ArrayList<>();

	/**
	 * 一局打几盘
	 */
	private int panShu;

	private ActionStatisticsListenerManager actionStatisticsListenerManager = new ActionStatisticsListenerManager();
	private PlayersMenFengDeterminer playersMenFengDeterminerForFirstPan;
	private ZhuangDeterminer zhuangDeterminerForFirstPan;
	private AvaliablePaiFiller avaliablePaiFiller;
	private GuipaiDeterminer guipaiDeterminer;
	private FaPaiStrategy faPaiStrategy;
	private CurrentPanFinishiDeterminer currentPanFinishiDeterminer;
	private GouXingPanHu GouXingPanHu;
	private CurrentPanResultBuilder currentPanResultBuilder;
	private JuResultBuilder juResultBuilder;
	private MajiangPlayerInitialActionUpdater initialActionUpdater;
	private MajiangPlayerMoActionProcessor moActionProcessor;
	private MajiangPlayerMoActionUpdater moActionUpdater;
	private MajiangPlayerDaActionProcessor daActionProcessor;
	private MajiangPlayerDaActionUpdater daActionUpdater;
	private MajiangPlayerChiActionProcessor chiActionProcessor;
	private MajiangPlayerChiActionUpdater chiActionUpdater;
	private MajiangPlayerPengActionProcessor pengActionProcessor;
	private MajiangPlayerPengActionUpdater pengActionUpdater;
	private MajiangPlayerGangActionProcessor gangActionProcessor;
	private MajiangPlayerGangActionUpdater gangActionUpdater;
	private MajiangPlayerGuoActionProcessor guoActionProcessor;
	private MajiangPlayerGuoActionUpdater guoActionUpdater;
	private MajiangPlayerHuActionProcessor huActionProcessor;

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

	public PanActionFrame updateInitialAction() throws Exception {
		initialActionUpdater.updateActions(this);
		return currentPan.recordPanActionFrame(null);
	}

	public PanActionFrame action(String playerId, int actionId) throws Exception {
		MajiangPlayerAction action = currentPan.findPlayerActionCandidate(playerId, actionId);
		if (action == null) {
			throw new MajiangPlayerActionNotFoundException();
		}
		doAction(action);
		return currentPan.recordPanActionFrame(action);
	}

	public boolean determineToFinishCurrentPan() {
		return currentPanFinishiDeterminer.determineToFinishCurrentPan(this);
	}

	public PanResult finishCurrentPan() {
		PanResult currentPanResult = currentPanResultBuilder.buildCurrentPanResult(this);
		addFinishedPanResult(currentPanResult);
		setCurrentPan(null);
		return currentPanResult;
	}

	public void addFinishedPanResult(PanResult panResult) {
		finishedPanResultList.add(panResult);
	}

	private void doAction(MajiangPlayerAction action) throws Exception {
		MajiangPlayerActionType actionType = action.getType();
		if (actionType.equals(MajiangPlayerActionType.mo)) {
			moActionProcessor.process((MajiangMoAction) action, this);
			// TODO listener
			moActionUpdater.updateActions((MajiangMoAction) action, this);
		} else if (actionType.equals(MajiangPlayerActionType.da)) {
			daActionProcessor.process((MajiangDaAction) action, this);
			actionStatisticsListenerManager.updateDaActionListener((MajiangDaAction) action, this);
			daActionUpdater.updateActions((MajiangDaAction) action, this);
		} else if (actionType.equals(MajiangPlayerActionType.chi)) {
			chiActionProcessor.process((MajiangChiAction) action, this);
			// TODO listener
			chiActionUpdater.updateActions((MajiangChiAction) action, this);
		} else if (actionType.equals(MajiangPlayerActionType.peng)) {
			pengActionProcessor.process((MajiangPengAction) action, this);
			// TODO listener
			pengActionUpdater.updateActions((MajiangPengAction) action, this);
		} else if (actionType.equals(MajiangPlayerActionType.gang)) {
			gangActionProcessor.process((MajiangGangAction) action, this);
			// TODO listener
			gangActionUpdater.updateActions((MajiangGangAction) action, this);
		} else if (actionType.equals(MajiangPlayerActionType.guo)) {
			guoActionProcessor.process((MajiangGuoAction) action, this);
			// TODO listener
			guoActionUpdater.updateActions((MajiangGuoAction) action, this);
		} else if (actionType.equals(MajiangPlayerActionType.hu)) {
			huActionProcessor.process((MajiangHuAction) action, this);
			// TODO listener?
			// TODO updater?
		} else {
		}

	}

	public void addActionStatisticsListener(MajiangPlayerActionStatisticsListener listener) {
		actionStatisticsListenerManager.addListener(listener);
	}

	public PanResult findLatestFinishedPanResult() {
		if (!finishedPanResultList.isEmpty()) {
			return finishedPanResultList.get(finishedPanResultList.size() - 1);
		} else {
			return null;
		}
	}

	public Pan getCurrentPan() {
		return currentPan;
	}

	public void setCurrentPan(Pan currentPan) {
		this.currentPan = currentPan;
	}

	public List<PanResult> getFinishedPanResultList() {
		return finishedPanResultList;
	}

	public void setFinishedPanResultList(List<PanResult> finishedPanResultList) {
		this.finishedPanResultList = finishedPanResultList;
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

	public CurrentPanFinishiDeterminer getCurrentPanFinishiDeterminer() {
		return currentPanFinishiDeterminer;
	}

	public void setCurrentPanFinishiDeterminer(CurrentPanFinishiDeterminer currentPanFinishiDeterminer) {
		this.currentPanFinishiDeterminer = currentPanFinishiDeterminer;
	}

	public GouXingPanHu getGouXingPanHu() {
		return GouXingPanHu;
	}

	public void setGouXingPanHu(GouXingPanHu gouXingPanHu) {
		GouXingPanHu = gouXingPanHu;
	}

	public CurrentPanResultBuilder getCurrentPanResultBuilder() {
		return currentPanResultBuilder;
	}

	public void setCurrentPanResultBuilder(CurrentPanResultBuilder currentPanResultBuilder) {
		this.currentPanResultBuilder = currentPanResultBuilder;
	}

	public JuResultBuilder getJuResultBuilder() {
		return juResultBuilder;
	}

	public void setJuResultBuilder(JuResultBuilder juResultBuilder) {
		this.juResultBuilder = juResultBuilder;
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

	public MajiangPlayerDaActionProcessor getDaActionProcessor() {
		return daActionProcessor;
	}

	public void setDaActionProcessor(MajiangPlayerDaActionProcessor daActionProcessor) {
		this.daActionProcessor = daActionProcessor;
	}

	public MajiangPlayerDaActionUpdater getDaActionUpdater() {
		return daActionUpdater;
	}

	public void setDaActionUpdater(MajiangPlayerDaActionUpdater daActionUpdater) {
		this.daActionUpdater = daActionUpdater;
	}

	public MajiangPlayerChiActionProcessor getChiActionProcessor() {
		return chiActionProcessor;
	}

	public void setChiActionProcessor(MajiangPlayerChiActionProcessor chiActionProcessor) {
		this.chiActionProcessor = chiActionProcessor;
	}

	public MajiangPlayerChiActionUpdater getChiActionUpdater() {
		return chiActionUpdater;
	}

	public void setChiActionUpdater(MajiangPlayerChiActionUpdater chiActionUpdater) {
		this.chiActionUpdater = chiActionUpdater;
	}

	public MajiangPlayerPengActionProcessor getPengActionProcessor() {
		return pengActionProcessor;
	}

	public void setPengActionProcessor(MajiangPlayerPengActionProcessor pengActionProcessor) {
		this.pengActionProcessor = pengActionProcessor;
	}

	public MajiangPlayerPengActionUpdater getPengActionUpdater() {
		return pengActionUpdater;
	}

	public void setPengActionUpdater(MajiangPlayerPengActionUpdater pengActionUpdater) {
		this.pengActionUpdater = pengActionUpdater;
	}

	public MajiangPlayerGangActionProcessor getGangActionProcessor() {
		return gangActionProcessor;
	}

	public void setGangActionProcessor(MajiangPlayerGangActionProcessor gangActionProcessor) {
		this.gangActionProcessor = gangActionProcessor;
	}

	public MajiangPlayerGangActionUpdater getGangActionUpdater() {
		return gangActionUpdater;
	}

	public void setGangActionUpdater(MajiangPlayerGangActionUpdater gangActionUpdater) {
		this.gangActionUpdater = gangActionUpdater;
	}

	public MajiangPlayerGuoActionProcessor getGuoActionProcessor() {
		return guoActionProcessor;
	}

	public void setGuoActionProcessor(MajiangPlayerGuoActionProcessor guoActionProcessor) {
		this.guoActionProcessor = guoActionProcessor;
	}

	public MajiangPlayerGuoActionUpdater getGuoActionUpdater() {
		return guoActionUpdater;
	}

	public void setGuoActionUpdater(MajiangPlayerGuoActionUpdater guoActionUpdater) {
		this.guoActionUpdater = guoActionUpdater;
	}

	public MajiangPlayerHuActionProcessor getHuActionProcessor() {
		return huActionProcessor;
	}

	public void setHuActionProcessor(MajiangPlayerHuActionProcessor huActionProcessor) {
		this.huActionProcessor = huActionProcessor;
	}

	public ActionStatisticsListenerManager getActionStatisticsListenerManager() {
		return actionStatisticsListenerManager;
	}

	public void setActionStatisticsListenerManager(ActionStatisticsListenerManager actionStatisticsListenerManager) {
		this.actionStatisticsListenerManager = actionStatisticsListenerManager;
	}

}
