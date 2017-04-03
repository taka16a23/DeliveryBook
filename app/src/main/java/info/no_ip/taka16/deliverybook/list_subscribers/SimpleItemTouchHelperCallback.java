package info.no_ip.taka16.deliverybook.list_subscribers;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    ListSubscribersAdapter mAdapter;

    public SimpleItemTouchHelperCallback(ListSubscribersAdapter adapter){
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        final int fromPos = viewHolder.getAdapterPosition();
        final int toPos = target.getAdapterPosition();
        mAdapter.notifyItemMoved(fromPos, toPos);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        final int fromPos = viewHolder.getAdapterPosition();
        mAdapter.notifyItemRemoved(fromPos);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            ListSubscribersAdapter.ViewHolder subscriberViewHolder = (ListSubscribersAdapter.ViewHolder) viewHolder;
            subscriberViewHolder.onItemSelected();
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        if (viewHolder instanceof ListSubscribersAdapter.ViewHolder) {
            // Tell the view holder it's time to restore the idle state
            ListSubscribersAdapter.ViewHolder itemViewHolder = (ListSubscribersAdapter.ViewHolder) viewHolder;
            itemViewHolder.onItemClear();
        }
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }
}
