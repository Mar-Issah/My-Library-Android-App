package com.siya.mylibraryrealworld;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.siya.mylibraryrealworld.BookActivity.BOOK_ID_KEY;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "BookRecyclerViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();
    private Context context;
    private String parentActivity;

    //we are going to call this adapter in different activities so you need the context and activity name
    public BookRecyclerViewAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        // ViewHolder holder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.textViewBookName.setText(books.get(position).getName());

        //for the images you need the glide library to load images into the image view from the internet
        //set it as bitmap and load into the card view the save image url
        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imageViewBook);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
                context.startActivity(intent);
            }
        });

        //setting the author name and short desc into the text view by getting it from shared preference
        holder.textViewAuthorName.setText(books.get(position).getAuthor());
        holder.textViewShortDesc.setText(books.get(position).getShortDesc());


        //when the down arrow is click expand the card view and vice versa, set the button vissiblity to gone
        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);                    //for animation on the root
            holder.expandableReLayout.setVisibility(View.VISIBLE);
            holder.btnDownArrow.setVisibility(View.GONE);

            if (parentActivity.equals("SeeAllBooksActivity")) {
                holder.textViewDelete.setVisibility(View.GONE);

            } else if (parentActivity.equals("AlreadyReadBookActivity")) {
                holder.textViewDelete.setVisibility(View.VISIBLE);
                holder.textViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);         //how to create a dialog box to show message before deleting book
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (UtilsDatabase.getInstance(context).removeFromAlreadyRead(books.get(position))) {
                                    Toast.makeText(context, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                           builder.create().show();
                    }
                });

            } else if (parentActivity.equals("WantToReadActivity")) {
                holder.textViewDelete.setVisibility(View.VISIBLE);
                holder.textViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);         //show the dialog box to receive confirmation before deleting
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (UtilsDatabase.getInstance(context).removeFromWantToRead(books.get(position))) {
                                    Toast.makeText(context, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });

            } else if (parentActivity.equals("CurrentlyReadingActivity")) {
                holder.textViewDelete.setVisibility(View.VISIBLE);
                holder.textViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);         //how to create a dialog box to show message before deleting book
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");       //message in the dialog box

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {                //yes = positive button; no= negative button
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (UtilsDatabase.getInstance(context).removeFromCurrentlyReading(books.get(position))) {
                                    Toast.makeText(context, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();             //notifies that a book has been remove so refresh and reposition
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();             //finally create and shoe the builder
                    }
                });

            } else {
                holder.textViewDelete.setVisibility(View.VISIBLE);
                holder.textViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);         //how to create a dialog box to show message before deleting book
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");       //the message

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {                //yes = positive button; no= negative button
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (UtilsDatabase.getInstance(context).removeFromFavoriteBooks(books.get(position))) {
                                    Toast.makeText(context, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();                         //notifies that a book has been remove so refresh and reposition//takes long to update use call back interfaces
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });

                }

        } else {
            TransitionManager.beginDelayedTransition(holder.parent);                    //for animation on the root, card view as the parent
            holder.expandableReLayout.setVisibility(View.GONE);
            holder.btnDownArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    //use to set books into adapter and refresh the data
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView imageViewBook;
        private TextView textViewBookName;

        private ImageView btnDownArrow, btnUpArrow;
        private RelativeLayout expandableReLayout;
        private TextView textViewAuthorName, textViewShortDesc;

        private TextView textViewDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            imageViewBook = itemView.findViewById(R.id.imageViewBook);
            textViewBookName = itemView.findViewById(R.id.textViewBookName);

            btnDownArrow = itemView.findViewById(R.id.btnDownArrow);
            btnUpArrow = itemView.findViewById(R.id.btnUpArrow);
            expandableReLayout = itemView.findViewById(R.id.expandableReLayout);
            textViewAuthorName = itemView.findViewById(R.id.textViewAuthorName);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);

            textViewDelete = itemView.findViewById(R.id.textViewDelete);

            //set listeners on the arrow key to invert the isExpanded
            btnDownArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            btnUpArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }
    }
}