package serveurcomm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import serveurcomm.modele.bean.CoordGps;
import serveurcomm.modele.bean.Mission;
import serveurcomm.modele.dao.CoordGpsDAO;
import serveurcomm.modele.dao.MissionDAO;
import serveurcomm.modele.dao.UtilisateurDAO;

@Controller
public class ControllerDefault {
	
	@Autowired
    private UtilisateurDAO utilisateurDao;
	
	@Autowired
    private CoordGpsDAO coordGpsDao;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView helloWorld(){
 
		ModelAndView model = new ModelAndView("accueil");
		model.addObject("titrePage", "accueil");
		model.addObject("msg", "hello world");
		model.addObject("nom", utilisateurDao.get(1).getNom());
		model.addObject("prenom", utilisateurDao.get(1).getPrenom());
		model.addObject("object", utilisateurDao.get(1));
		model.addObject("autre", "Message spécial");
		return model;
	}
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public ModelAndView map(){
 
		ModelAndView model = new ModelAndView("map");
		model.addObject("titrePage", "Carte du Canal du Midi");
		model.addObject("lat", Double.toString(coordGpsDao.get(1).getLattitude()));
		model.addObject("long", Double.toString(coordGpsDao.get(1).getLongitude()));
		List<CoordGps> liste = coordGpsDao.list();
		model.addObject("scriptPoints", this.GetPoints(liste));
		model.addObject("scriptPolyline", this.GetPolyline(liste.size()));
		model.addObject("lastPosIndex", Integer.toString(liste.size()));
		return model;
	}

	private String GetPoints(List<CoordGps> liste) {
		String script = "";
		int index = 1;
		for (CoordGps coordGps : liste) {
			script += "var pos" + index + "=new google.maps.LatLng(" + coordGps.getLattitude() + ", " + coordGps.getLongitude() + ");\r\n";
			index++;
		}
		return script;
	}
	
	private String GetPolyline(int listSize) {
		String script = "[";
		for (int i = 1; i < listSize; i++) {
			script += "pos" + i + ",";
		}
		script += "pos" + listSize + "]\r\n";
		return script;
	}
	
	@RequestMapping(value = "/creationMission", method = RequestMethod.GET)
	public ModelAndView creationMission(){
		
		ModelAndView model = new ModelAndView("creationMission");
		System.out.println("Configuration de la mission");
		
		return null;
		//return model;
	}
	
	@RequestMapping(value = "/enregistrerMission", method = RequestMethod.POST)
    public ModelAndView enregistrerMission(@ModelAttribute Mission mission) {
		/**
		 * Contrôle de la cohérence de la mission
		 */
		MissionDAO missionDao = new MissionDAO();
		missionDao.saveOrUpdate(mission);
		
		ModelAndView model = new ModelAndView("recapCreationMission");
		model.addObject("mission", mission);
		
		System.out.println("Mission enregistré");
		return null;
        //return model;
    }
	
	@RequestMapping(value = "/lancementMission", method = RequestMethod.POST)
    public ModelAndView lancementMission(@ModelAttribute Mission mission) {
		/**
		 * Contrôle de la cohérence de la mission
		 */
		System.out.println("Vérification de la liste des drones");
		envoiMissionDrone();
		
		ModelAndView model = new ModelAndView("validationLancementMission");
		model.addObject("mission", mission);
		
		return null;
        //return model;
    }

	@RequestMapping(value = "/transfertTampon", method = RequestMethod.POST)
    public ModelAndView transfertTampon(@RequestParam("donnees_json") String donnees_json, 
    		@RequestParam("id_mission") String id_mission) {
		/**
		 * Contrôle de la cohérence de la mission
		 */
		System.out.println("Récupération des données partielles de la mission");
		enregistrerDonneesTampon(donnees_json, id_mission);

		
		System.out.println("Mission enregistré");
		return null;
        //return model;
    }
	
	
	private void enregistrerDonneesTampon(String donnees_json, String id_mission) {
		System.out.println("Enregistrement des données partielles en base");
	}

	/**
	 * méthode présente dans une classe de service qui contrôle les objets distribués
	 */
	private void envoiMissionDrone() {
		System.out.println("Transmission de la mission au drone");
	}
	
	
}