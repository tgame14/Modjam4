package com.tgame.modjam4.api;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.tgame.modjam4.spells.ISpell;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState;

/**
 * @since 15/05/14
 * @author tgame14
 */
public class SpellRegistry
{
    public static SpellRegistry INSTANCE = new SpellRegistry();
    private SpellRegistry() {}

    protected ImmutableList<String> spellList;
    protected ImmutableBiMap<String, ISpell> spellMap;

    private ImmutableList.Builder<String> listBuilder = new ImmutableList.Builder<String>();
    private ImmutableBiMap.Builder<String, ISpell> mapBuilder = new ImmutableBiMap.Builder<String, ISpell>();

    public void addSpell(ISpell spell, String key)
    {
        if (Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION))
        {
            throw new UnsupportedOperationException("Must register spells before postinit!");
        }
        listBuilder.add(key);
        mapBuilder.put(key, spell);
    }

    public void onPost()
    {
        this.spellList = listBuilder.build();
        this.spellMap = mapBuilder.build();
        listBuilder = null;
        mapBuilder = null;
    }

    public ISpell getSpell(String key)
    {
        return spellMap.get(key);
    }

    public String getFirst()
    {
        return spellList.get(0);
    }

    public String getNext(String key)
    {
        if (spellList.contains(key))
        {
            return spellList.get(spellList.indexOf(key));
        }
    }
}
