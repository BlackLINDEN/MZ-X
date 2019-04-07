package blacklinden.com.cannabisgrowthsimulator.pojo;

public class SelectableStashItem  extends Stash{
        private boolean isSelected = false;


        public SelectableStashItem(Stash stash,boolean isSelected) {
            super(stash.getId(),stash.getFajta(),stash.getMennyi());
            this.isSelected = isSelected;
        }


        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

