package dev.tsnanh.vku.view.forum

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialFadeThrough
import dev.tsnanh.vku.R
import dev.tsnanh.vku.adapters.ForumAdapter
import dev.tsnanh.vku.adapters.ForumClickListener
import dev.tsnanh.vku.databinding.FragmentForumBinding
import dev.tsnanh.vku.domain.Resource

class ForumFragment : Fragment() {

    private lateinit var binding: FragmentForumBinding
    private val viewModel: ForumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough.create(requireContext())
        exitTransition = MaterialFadeThrough.create(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_forum, container, false)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.listTopics.setHasFixedSize(true)
        val rotation =
            (requireContext()
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.rotation
        if (rotation == 0) {
            binding.listTopics.layoutManager = LinearLayoutManager(requireContext())
        } else {
            binding.listTopics.layoutManager = GridLayoutManager(requireContext(), 2)
        }

        val adapter = ForumAdapter(ForumClickListener {
            viewModel.onItemClick(it)
        })

        binding.listTopics.adapter = adapter

        viewModel.forums.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Resource.Success -> {
                        adapter.submitList(it.data)
                        binding.progressBar.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        Snackbar.make(requireView(), it.message.toString(), Snackbar.LENGTH_LONG)
                            .show()
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        })

        viewModel.navigateToListThread.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                    ForumFragmentDirections.actionNavigationForumToNavigationThread(it.id, it.title)
                )
                viewModel.onItemClicked()
            }
        })

        binding.fabNewThread.setOnClickListener {
            val extras = FragmentNavigatorExtras(
                binding.fabNewThread to "fab_submit"
            )
            findNavController().navigate(
                ForumFragmentDirections.actionNavigationForumToNavigationNewThread(),
                extras
            )
        }
    }

}
