package com.ivanwidyan.postapp.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ivanwidyan.postapp.model.Post;
import com.ivanwidyan.postapp.R;
import com.ivanwidyan.postapp.utils.Constants;
import com.squareup.picasso.Picasso;

public class TabPostFragment extends Fragment {

    private View mRootView;
    private RecyclerView mPostList;
    private FirebaseRecyclerAdapter<Post, PostViewHolder> mPostAdapter;
    private DatabaseReference mPostRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_tab_post,container,false);

        initializeScreen();

        return mRootView;
    }

    private void initializeScreen () {
        mPostList = (RecyclerView) mRootView.findViewById(R.id.post_list);
        mPostList.setHasFixedSize(true);
        mPostRef = FirebaseDatabase.getInstance().getReference().child(Constants.POSTS);

        setupAdapter();

        mPostList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setupAdapter() {
        mPostAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(
                Post.class,
                R.layout.post_row,
                PostViewHolder.class,
                mPostRef
        ) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, Post model, int position) {

                viewHolder.SetTitle(model.getTitle());
                viewHolder.SetDesc(model.getDesc());
                viewHolder.SetImage(getContext(), model.getImage());
            }
        };

        mPostList.setAdapter(mPostAdapter);
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public PostViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void SetTitle(String title) {

            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);

        }

        public void SetDesc(String desc) {

            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);

        }

        public void SetImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);
        }

    }
}
