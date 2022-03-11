package me.dio.simulator.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.service.autofill.FieldClassification;
import android.service.autofill.FieldClassification.Match;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import me.dio.simulator.databinding.MatchItemBinding;
import me.dio.simulator.ui.DetailActivity2;

public class matchesAdapter extends RecyclerView.Adapter <matchesAdapter.ViewHolder>{

    private List<Match> matches;
    public matchesAdapter(List<Match> matches) { this.matches = matches; }

    public List<Match> getMatches() {
        return matches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        MatchItemBinding binding = MatchItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext(position);
    Match match = matches.get(position);
    // Adaptaa os dados da partida (recuperar da API) para o nosso layout.
        Glide.with(context).load(match.getHomeTeam().getImage()).into(holder.binding.ivHometeam);
    holder.binding.tvHomeTeamName.setText(match.getHomeTeam().getName());
    holder.binding.tvHomeTemaScore.setText(match.getHomeTeam().getName());
    if (match.getHomeTeam().getScore() != null){
        holder.binding.tvHomeTemaScore.setText(String.valueOf(match.getHomeTeam().getScore()));
    }
        Glide.with(context).load(match.getAwayTeam().getImage()).into(holder.binding.ivAwayTeam);
    holder.binding.tvAwayTEamName.setText(match.getAwayTeam().getName());

        if (match.getAwayTeam().getScore() != null){
            holder.binding.tvHomeTemaScore.setText(String.valueOf(match.getAwayeam().getScore()));
        }
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity2.class);
            intent.putExtra(DetailActivity2.Extras.MATCH, match);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MatchItemBinding binding;
        public ViewHolder(MatchItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
