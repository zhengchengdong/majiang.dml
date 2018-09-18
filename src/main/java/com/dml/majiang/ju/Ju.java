package com.dml.majiang.ju;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.dml.majiang.ju.finish.JuFinishiDeterminer;
import com.dml.majiang.ju.firstpan.StartFirstPanProcess;
import com.dml.majiang.ju.nextpan.CreateNextPanDeterminer;
import com.dml.majiang.ju.nextpan.StartNextPanProcess;
import com.dml.majiang.ju.result.JuResult;
import com.dml.majiang.ju.result.JuResultBuilder;
import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.pan.Pan;
import com.dml.majiang.pan.avaliablepai.AvaliablePaiFiller;
import com.dml.majiang.pan.fapai.FaPaiStrategy;
import com.dml.majiang.pan.finish.CurrentPanFinishiDeterminer;
import com.dml.majiang.pan.frame.PanActionFrame;
import com.dml.majiang.pan.guipai.GuipaiDeterminer;
import com.dml.majiang.pan.publicwaitingplayer.CurrentPanPublicWaitingPlayerDeterminer;
import com.dml.majiang.pan.result.CurrentPanResultBuilder;
import com.dml.majiang.pan.result.PanResult;
import com.dml.majiang.player.action.MajiangPlayerAction;
import com.dml.majiang.player.action.MajiangPlayerActionNotFoundException;
import com.dml.majiang.player.action.MajiangPlayerActionType;
import com.dml.majiang.player.action.chi.MajiangChiAction;
import com.dml.majiang.player.action.chi.MajiangPlayerChiActionProcessor;
import com.dml.majiang.player.action.chi.MajiangPlayerChiActionUpdater;
import com.dml.majiang.player.action.da.MajiangDaAction;
import com.dml.majiang.player.action.da.MajiangPlayerDaActionProcessor;
import com.dml.majiang.player.action.da.MajiangPlayerDaActionUpdater;
import com.dml.majiang.player.action.gang.MajiangGangAction;
import com.dml.majiang.player.action.gang.MajiangPlayerGangActionProcessor;
import com.dml.majiang.player.action.gang.MajiangPlayerGangActionUpdater;
import com.dml.majiang.player.action.guo.MajiangGuoAction;
import com.dml.majiang.player.action.guo.MajiangPlayerGuoActionProcessor;
import com.dml.majiang.player.action.guo.MajiangPlayerGuoActionUpdater;
import com.dml.majiang.player.action.hu.MajiangHuAction;
import com.dml.majiang.player.action.hu.MajiangPlayerHuActionProcessor;
import com.dml.majiang.player.action.hu.MajiangPlayerHuActionUpdater;
import com.dml.majiang.player.action.initial.MajiangPlayerInitialActionUpdater;
import com.dml.majiang.player.action.listener.ActionStatisticsListenerManager;
import com.dml.majiang.player.action.listener.MajiangPlayerActionStatisticsListener;
import com.dml.majiang.player.action.mo.MajiangMoAction;
import com.dml.majiang.player.action.mo.MajiangPlayerMoActionProcessor;
import com.dml.majiang.player.action.mo.MajiangPlayerMoActionUpdater;
import com.dml.majiang.player.action.peng.MajiangPengAction;
import com.dml.majiang.player.action.peng.MajiangPlayerPengActionProcessor;
import com.dml.majiang.player.action.peng.MajiangPlayerPengActionUpdater;
import com.dml.majiang.player.menfeng.PlayersMenFengDeterminer;
import com.dml.majiang.player.shoupai.gouxing.GouXingPanHu;
import com.dml.majiang.player.zhuang.ZhuangDeterminer;

/**
 * 一局麻将
 * 
 * @author Neo
 *
 */
public class Ju {

	private Pan currentPan;

	private List<PanResult> finishedPanResultList = new ArrayList<>();

	private JuResult juResult;

	private StartFirstPanProcess startFirstPanProcess;
	private StartNextPanProcess startNextPanProcess;
	private ActionStatisticsListenerManager actionStatisticsListenerManager = new ActionStatisticsListenerManager();
	private PlayersMenFengDeterminer playersMenFengDeterminerForFirstPan;
	private PlayersMenFengDeterminer playersMenFengDeterminerForNextPan;
	private ZhuangDeterminer zhuangDeterminerForFirstPan;
	private ZhuangDeterminer zhuangDeterminerForNextPan;
	private AvaliablePaiFiller avaliablePaiFiller;
	private GuipaiDeterminer guipaiDeterminer;
	private FaPaiStrategy faPaiStrategy;
	private CurrentPanFinishiDeterminer currentPanFinishiDeterminer;
	private GouXingPanHu GouXingPanHu;
	private CurrentPanPublicWaitingPlayerDeterminer currentPanPublicWaitingPlayerDeterminer;
	private CurrentPanResultBuilder currentPanResultBuilder;
	private CreateNextPanDeterminer createNextPanDeterminer;
	private JuFinishiDeterminer juFinishiDeterminer;
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
	private MajiangPlayerHuActionUpdater huActionUpdater;

	public boolean determineToCreateNextPan() {
		return createNextPanDeterminer.determineToCreateNextPan(this);
	}

	public void determinePlayersMenFengForFirstPan() throws Exception {
		playersMenFengDeterminerForFirstPan.determinePlayersMenFeng(this);
		// TODO 或许每个determiner运行过之后都需要统计一些个性化的信息，比如目前谁连庄了几次
	}

	public void determinePlayersMenFengForNextPan() throws Exception {
		playersMenFengDeterminerForNextPan.determinePlayersMenFeng(this);
	}

	public void determineZhuangForFirstPan() throws Exception {
		zhuangDeterminerForFirstPan.determineZhuang(this);
	}

	public void determineZhuangForNextPan() throws Exception {
		zhuangDeterminerForNextPan.determineZhuang(this);
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
		return currentPan.recordPanActionFrame(null, 0);
	}

	public PanActionFrame action(String playerId, int actionId, long actionTime) throws Exception {
		MajiangPlayerAction action = currentPan.findPlayerActionCandidate(playerId, actionId);
		if (action == null) {
			throw new MajiangPlayerActionNotFoundException();
		}
		doAction(action);
		currentPanPublicWaitingPlayerDeterminer.determinePublicWaitingPlayer(this);
		PanActionFrame panActionFrame = currentPan.recordPanActionFrame(action, actionTime);
		// action之后要试探一盘是否结束
		if (currentPanFinishiDeterminer.determineToFinishCurrentPan(this)) {
			finishCurrentPan(actionTime);
			// 试探一局是否结束
			if (juFinishiDeterminer.determineToFinishJu(this)) {
				finish();
			}
		}
		return panActionFrame;
	}

	public void finish() {
		juResult = juResultBuilder.buildJuResult(this);
	}

	public void finishCurrentPan(long finishTime) {
		PanResult currentPanResult = currentPanResultBuilder.buildCurrentPanResult(this, finishTime);
		addFinishedPanResult(currentPanResult);
		setCurrentPan(null);
	}

	public void addFinishedPanResult(PanResult panResult) {
		finishedPanResultList.add(panResult);
	}

	private void doAction(MajiangPlayerAction action) throws Exception {
		MajiangPlayerActionType actionType = action.getType();
		if (actionType.equals(MajiangPlayerActionType.mo)) {
			moActionProcessor.process((MajiangMoAction) action, this);
			actionStatisticsListenerManager.updateMoActionListener((MajiangMoAction) action, this);
			moActionUpdater.updateActions((MajiangMoAction) action, this);
		} else if (actionType.equals(MajiangPlayerActionType.da)) {
			daActionProcessor.process((MajiangDaAction) action, this);
			actionStatisticsListenerManager.updateDaActionListener((MajiangDaAction) action, this);
			daActionUpdater.updateActions((MajiangDaAction) action, this);
		} else if (actionType.equals(MajiangPlayerActionType.chi)) {
			chiActionProcessor.process((MajiangChiAction) action, this);
			actionStatisticsListenerManager.updateChiActionListener((MajiangChiAction) action, this);
			chiActionUpdater.updateActions((MajiangChiAction) action, this);
		} else if (actionType.equals(MajiangPlayerActionType.peng)) {
			pengActionProcessor.process((MajiangPengAction) action, this);
			actionStatisticsListenerManager.updatePengActionListener((MajiangPengAction) action, this);
			pengActionUpdater.updateActions((MajiangPengAction) action, this);
		} else if (actionType.equals(MajiangPlayerActionType.gang)) {
			gangActionProcessor.process((MajiangGangAction) action, this);
			actionStatisticsListenerManager.updateGangActionListener((MajiangGangAction) action, this);
			gangActionUpdater.updateActions((MajiangGangAction) action, this);
		} else if (actionType.equals(MajiangPlayerActionType.guo)) {
			guoActionProcessor.process((MajiangGuoAction) action, this);
			actionStatisticsListenerManager.updateGuoActionListener((MajiangGuoAction) action, this);
			guoActionUpdater.updateActions((MajiangGuoAction) action, this);
		} else if (actionType.equals(MajiangPlayerActionType.hu)) {
			huActionProcessor.process((MajiangHuAction) action, this);
			// TODO listener?
			huActionUpdater.updateActions((MajiangHuAction) action, this);
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

	public int countFinishedPan() {
		return finishedPanResultList.size();
	}

	public void startFirstPan(List<String> allPlayerIds) throws Exception {
		startFirstPanProcess.startFirstPan(this, allPlayerIds);
	}

	public void startNextPan() throws Exception {
		actionStatisticsListenerManager.updateListenersForNextPan();
		createNextPanDeterminer.reset();
		startNextPanProcess.startNextPan(this);
	}

	public StartFirstPanProcess getStartFirstPanProcess() {
		return startFirstPanProcess;
	}

	public void updateShoupaiListSortComparatorForAllPlayersInCurrentPan(Comparator<MajiangPai> comparator) {
		currentPan.updateShoupaiListSortComparatorForAllPlayers(comparator);
	}

	public void setStartFirstPanProcess(StartFirstPanProcess startFirstPanProcess) {
		this.startFirstPanProcess = startFirstPanProcess;
	}

	public StartNextPanProcess getStartNextPanProcess() {
		return startNextPanProcess;
	}

	public void setStartNextPanProcess(StartNextPanProcess startNextPanProcess) {
		this.startNextPanProcess = startNextPanProcess;
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

	public JuResult getJuResult() {
		return juResult;
	}

	public void setJuResult(JuResult juResult) {
		this.juResult = juResult;
	}

	public PlayersMenFengDeterminer getPlayersMenFengDeterminerForFirstPan() {
		return playersMenFengDeterminerForFirstPan;
	}

	public void setPlayersMenFengDeterminerForFirstPan(PlayersMenFengDeterminer playersMenFengDeterminerForFirstPan) {
		this.playersMenFengDeterminerForFirstPan = playersMenFengDeterminerForFirstPan;
	}

	public PlayersMenFengDeterminer getPlayersMenFengDeterminerForNextPan() {
		return playersMenFengDeterminerForNextPan;
	}

	public void setPlayersMenFengDeterminerForNextPan(PlayersMenFengDeterminer playersMenFengDeterminerForNextPan) {
		this.playersMenFengDeterminerForNextPan = playersMenFengDeterminerForNextPan;
	}

	public ZhuangDeterminer getZhuangDeterminerForFirstPan() {
		return zhuangDeterminerForFirstPan;
	}

	public void setZhuangDeterminerForFirstPan(ZhuangDeterminer zhuangDeterminerForFirstPan) {
		this.zhuangDeterminerForFirstPan = zhuangDeterminerForFirstPan;
	}

	public ZhuangDeterminer getZhuangDeterminerForNextPan() {
		return zhuangDeterminerForNextPan;
	}

	public void setZhuangDeterminerForNextPan(ZhuangDeterminer zhuangDeterminerForNextPan) {
		this.zhuangDeterminerForNextPan = zhuangDeterminerForNextPan;
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

	public CurrentPanPublicWaitingPlayerDeterminer getCurrentPanPublicWaitingPlayerDeterminer() {
		return currentPanPublicWaitingPlayerDeterminer;
	}

	public void setCurrentPanPublicWaitingPlayerDeterminer(
			CurrentPanPublicWaitingPlayerDeterminer currentPanPublicWaitingPlayerDeterminer) {
		this.currentPanPublicWaitingPlayerDeterminer = currentPanPublicWaitingPlayerDeterminer;
	}

	public CurrentPanResultBuilder getCurrentPanResultBuilder() {
		return currentPanResultBuilder;
	}

	public void setCurrentPanResultBuilder(CurrentPanResultBuilder currentPanResultBuilder) {
		this.currentPanResultBuilder = currentPanResultBuilder;
	}

	public CreateNextPanDeterminer getCreateNextPanDeterminer() {
		return createNextPanDeterminer;
	}

	public void setCreateNextPanDeterminer(CreateNextPanDeterminer createNextPanDeterminer) {
		this.createNextPanDeterminer = createNextPanDeterminer;
	}

	public JuFinishiDeterminer getJuFinishiDeterminer() {
		return juFinishiDeterminer;
	}

	public void setJuFinishiDeterminer(JuFinishiDeterminer juFinishiDeterminer) {
		this.juFinishiDeterminer = juFinishiDeterminer;
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

	public MajiangPlayerHuActionUpdater getHuActionUpdater() {
		return huActionUpdater;
	}

	public void setHuActionUpdater(MajiangPlayerHuActionUpdater huActionUpdater) {
		this.huActionUpdater = huActionUpdater;
	}

}
