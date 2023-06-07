package crazypants.enderio.conduit.item.filter;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import crazypants.enderio.EnderIOTab;
import crazypants.enderio.ModObject;

public class ItemBigItemFilter extends ItemBasicItemFilter {

    public static ItemBigItemFilter create() {
        ItemBigItemFilter result = new ItemBigItemFilter();
        result.init();
        return result;
    }

    private final IIcon[] icons;

    protected ItemBigItemFilter() {
        setCreativeTab(EnderIOTab.tabEnderIO);
        setUnlocalizedName(ModObject.itemBigFilterUpgrade.unlocalisedName);
        setHasSubtypes(true);
        setMaxDamage(0);
        setMaxStackSize(64);
        icons = new IIcon[2];
    }

    protected void init() {
        GameRegistry.registerItem(this, ModObject.itemBigFilterUpgrade.unlocalisedName);
    }

    @Override
    public IItemFilter createFilterFromStack(ItemStack stack) {
        int damage = MathHelper.clamp_int(stack.getItemDamage(), 0, 1);
        ItemFilter filter;
        if (damage == 0) {
            filter = new ItemFilterBig(24, false);
        } else {
            filter = new ItemFilterBig(24, true);
        }
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("filter")) {
            filter.readFromNBT(stack.stackTagCompound.getCompoundTag("filter"));
        }
        return filter;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        damage = MathHelper.clamp_int(damage, 0, 1);
        return icons[damage];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister IIconRegister) {
        icons[0] = IIconRegister.registerIcon("enderio:filterUpgradeBig");
        icons[1] = IIconRegister.registerIcon("enderio:filterUpgradeBigAdvanced");
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 1);
        return i == 0 ? "enderio.filterUpgradeBig" : "enderio.filterUpgradeBigAdvanced";
    }
}
