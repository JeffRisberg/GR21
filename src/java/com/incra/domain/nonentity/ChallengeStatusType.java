package com.incra.domain.nonentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <i>ChallengeStatusType</i> enum is used to drive the pull-downs on the
 * admin screens which control if a challenge is Launched, Pending, a Template,
 * a CYOC Template, an Incentive Program, etc.
 * 
 * While this enum dates back to early 2010, it was moved into the forefront
 * during Sept/October 2012, when we were clarifying the data model.
 * 
 * @author Jeff Risberg
 * @since January 2010
 */
public enum ChallengeStatusType {
  /* */Launched("Launched"),
  /* */Template("Template"),
  /* */Pending("Pending");

  protected String label;

  /** Constructor */
  private ChallengeStatusType(String label) {
    this.label = label;

    ObjectRepo.shared_objectList.add(this);
    ObjectRepo.shared_keyToObjectMap.put(this.name(), this);
  }

  public String getLabel() {
    return label;
  }

  public String getKeyInDatabase() {
    if (name() == "Launched") {
      return "";
    } else {
      return name();
    }
  }

  static public List<ChallengeStatusType> selectAll() {
    return ObjectRepo.shared_objectList;
  }

  static public ChallengeStatusType findByKey(String key) {
    if (key == null || key == "") {
      return Launched;
    } else {
      return ObjectRepo.shared_keyToObjectMap.get(key);
    }
  }

  static protected class ObjectRepo {
    static protected Map<String, ChallengeStatusType> shared_keyToObjectMap = new HashMap<String, ChallengeStatusType>();
    static protected List<ChallengeStatusType> shared_objectList = new ArrayList<ChallengeStatusType>();
  }
}