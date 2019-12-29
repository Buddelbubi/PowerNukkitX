package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;

import java.util.concurrent.ThreadLocalRandom;

public class BlockCoral extends BlockFlowable {
    public static final int TYPE_TUBE = 0;
    public static final int TYPE_BRAIN = 1;
    public static final int TYPE_FIRE = 2;
    public static final int TYPE_HORN = 3;
    
    public BlockCoral() {
        this(0);
    }
    
    public BlockCoral(int meta) {
        super(meta);
    }
    
    @Override
    public void setDamage(int meta) {
        super.setDamage(meta);
    }
    
    @Override
    public int getId() {
        return CORAL;
    }
    
    public boolean isDead() {
        return (getDamage() & 0x8) == 0x8;
    }
    
    public void setDead(boolean dead) {
        if (dead) {
            setDamage(getDamage() | 0x8);
        } else {
            setDamage(getDamage() ^ 0x8);
        }
    }
    
    @Override
    public int getWaterloggingLevel() {
        return 2;
    }
    
    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            Block down = down();
            if (!down.isSolid() || down.getId() == Block.MAGMA) {
                this.getLevel().useBreakOn(this);
            } else if (!isDead()) {
                this.getLevel().scheduleUpdate(this, 60 + ThreadLocalRandom.current().nextInt(40));
            }
            return type;
        } else if (type == Level.BLOCK_UPDATE_SCHEDULED) {
            if (!isDead() && !(getLevelBlockAtLayer(1) instanceof BlockWater)) {
                setDead(true);
                this.getLevel().setBlock(this, this, true, true);
            }
            return type;
        }
        return 0;
    }
    
    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        Block down = down();
        Block layer1 = block.getLevelBlockAtLayer(1);
        if (layer1.getId() != Block.AIR && (!(layer1 instanceof BlockWater) || layer1.getDamage() != 0)) {
            return false;
        }
        
        if (down.isSolid()) {
            if (layer1.getId() != Block.AIR) {
                this.getLevel().setBlock(this, 1, block, true, false);
            }
            this.getLevel().setBlock(this, 0, this, true, true);
            return true;
        }
        return false;
    }
    
    @Override
    public String getName() {
        String[] names = new String[] {
                "Tube Coral",
                "Brain Coral",
                "Bubble Coral",
                "Fire Coral",
                "Horn Coral",
                // Invalid
                "Tube Coral",
                "Tube Coral",
                "Tube Coral"
        };
        String name = names[getDamage() & 0x7];
        if (isDead()) {
            return "Dead " + name;
        } else {
            return name;
        }
    }
    
    @Override
    public BlockColor getColor() {
        if (isDead()) {
            return BlockColor.GRAY_BLOCK_COLOR;
        }
        
        BlockColor[] colors = new BlockColor[] {
                BlockColor.BLUE_BLOCK_COLOR,
                BlockColor.PINK_BLOCK_COLOR,
                BlockColor.PURPLE_BLOCK_COLOR,
                BlockColor.RED_BLOCK_COLOR,
                BlockColor.YELLOW_BLOCK_COLOR,
                // Invalid
                BlockColor.BLUE_BLOCK_COLOR,
                BlockColor.BLUE_BLOCK_COLOR,
                BlockColor.BLUE_BLOCK_COLOR
        };
        return colors[getDamage() & 0x7];
    }
    
    @Override
    public boolean canSilkTouch() {
        return true;
    }
    
    @Override
    public Item[] getDrops(Item item) {
        if (item.getEnchantment(Enchantment.ID_SILK_TOUCH) != null) {
            return super.getDrops(item);
        } else {
            return new Item[0];
        }
    }
}