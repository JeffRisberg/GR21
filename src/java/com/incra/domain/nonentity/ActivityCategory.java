package com.incra.domain.nonentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <i>ActivityCategory</i> enumeration defines the primary groupings of
 * activities.
 * 
 * @author Jeff Risberg
 * @since November 2010
 */
public enum ActivityCategory {
  /* */PHYSICAL(1, "Physical", "Physical Activities"),
  /* */WELLNESS(2, "Wellness", "Wellness"),
  /* */FOOD(3, "Food & Drink", "Food and Drink");

  public int id;
  public String name;
  public String longName;

  private ActivityCategory(int id, String name, String longName) {
    this.id = id;
    this.name = name;
    this.longName = longName;

    ObjectRepo.shared_objectList.add(this);
    ObjectRepo.shared_keyToObjectMap.put(id, this);
  }

  public String toString() {
    return name;
  }

  static public List<ActivityCategory> selectAll() {
    return ObjectRepo.shared_objectList;
  }

  static public ActivityCategory findByKey(Integer key) {
    return ObjectRepo.shared_keyToObjectMap.get(key);
  }

  static protected class ObjectRepo {
    static protected Map<Integer, ActivityCategory> shared_keyToObjectMap = new HashMap<Integer, ActivityCategory>();
    static protected List<ActivityCategory> shared_objectList = new ArrayList<ActivityCategory>();
  }
}