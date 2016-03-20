package pl.mareklangiewicz.myfragments


import android.os.Bundle
import android.view.*
import pl.mareklangiewicz.myviews.MyViewDecorator


class MyDecoratorsTestsFragment : MyFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState) //just for logging

        inflateHeader(R.layout.mf_my_basic_header)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.mf_my_decorators_tests_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) = inflater.inflate(R.menu.mf_my_decorators, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_decorate_views -> {
                val view = view
                if (view == null) {
                    log.e("The root view of this fragment is null.")
                    return false
                }
                MyViewDecorator.decorateTree(view, "decorate", R.layout.mv_example_decoration, null)
                return true
            }
        }
        return false
    }
}
