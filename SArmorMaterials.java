package com.napzzzz.scratch;

import com.napzzzz.scratch.item.SItem;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum SArmorMaterials implements ArmorMaterial {
    CITROSE("citrose", 25,Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266655_) -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 4);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 7);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266655_.put(ArmorItem.Type.HELMET, 3);
    }), 19, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
        return Ingredient.of(SItem.CITROSE_INGOT.get());
    }),
    HEARTSTONE("heartstone", 25,Util.make(new EnumMap<>(ArmorItem.Type.class), (p_269655_) -> {
        p_269655_.put(ArmorItem.Type.BOOTS, 5);
        p_269655_.put(ArmorItem.Type.LEGGINGS, 7);
        p_269655_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_269655_.put(ArmorItem.Type.HELMET, 4);
    }), 19, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
        return Ingredient.of(SItem.HTS.get());
    }),
    ORCHSTONE("orchstone", 25,Util.make(new EnumMap<>(ArmorItem.Type.class), (p_269655_) -> {
        p_269655_.put(ArmorItem.Type.BOOTS, 4);
        p_269655_.put(ArmorItem.Type.LEGGINGS, 7);
        p_269655_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_269655_.put(ArmorItem.Type.HELMET, 3);
    }), 19, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
        return Ingredient.of(SItem.ORCH.get());
    });

    public static final StringRepresentable.EnumCodec<ArmorMaterials> CODEC = StringRepresentable.fromEnum(ArmorMaterials::values);
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private SArmorMaterials(String p_268171_, int p_268303_, EnumMap<ArmorItem.Type, Integer> p_267941_, int p_268086_, SoundEvent p_268145_, float p_268058_, float p_268180_, Supplier<Ingredient> p_268256_) {
        this.name = p_268171_;
        this.durabilityMultiplier = p_268303_;
        this.protectionFunctionForType = p_267941_;
        this.enchantmentValue = p_268086_;
        this.sound = p_268145_;
        this.toughness = p_268058_;
        this.knockbackResistance = p_268180_;
        this.repairIngredient = new LazyLoadedValue<>(p_268256_);
    }

    public int getDurabilityForType(ArmorItem.Type p_266745_) {
        return HEALTH_FUNCTION_FOR_TYPE.get(p_266745_) * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type p_266752_) {
        return this.protectionFunctionForType.get(p_266752_);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return Scratch.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public String getSerializedName() {
        return this.name;
    }
}