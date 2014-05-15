package com.tgame.modjam4;

import com.tgame.modjam4.api.SpellRegistry;

/**
 * @since 15/05/14
 * @author tgame14
 */
public class CommonProxy
{
    public void preInit()
    {

    }

    public void init()
    {

    }

    public void postInit()
    {
        SpellRegistry.INSTANCE.onPost();
    }
}
